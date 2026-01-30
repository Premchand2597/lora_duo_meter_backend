package com.loraDuoMeter.Service;


import java.time.format.DateTimeFormatter;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.loraDuoMeter.DTO.MQTT_Listner_Receive_Data_Dto;
import com.loraDuoMeter.Uplink_DTO.*;
import com.loraDuoMeter.Enum.PacketType;
import com.loraDuoMeter.Decoder.*;
import com.loraDuoMeter.Downlink_DTO.Instantaneous_Data_Downlink_Dto;
import com.loraDuoMeter.Downlink_DTO.Meter_Info_Downlink_Dto;
import com.loraDuoMeter.Downlink_Service.*;
import com.loraDuoMeter.JSON.UplinkJsonParser;
import com.loraDuoMeter.Proj.Mqtt_Api_Key_Proj;
import com.loraDuoMeter.Repo.IMqtt_Api_Key_Repo;
import com.loraDuoMeter.Uplink_Service.*;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;




@Service
public class Mqtt_Listner_Service {	
	
	@Autowired
	private IMqtt_Api_Key_Repo mqtt_repo;	
	
	@Autowired
	private Daily_Update_Uplink_Service daily_update_service;
	
	@Autowired
	private Battery_Cut_Off_Uplink_Service batter_cut_off_service;
	
	@Autowired
	private Device_Event_Notification_Uplink_Service device_event_notification_service;
	
	@Autowired
	private Gas_Leak_Uplink_Service gas_leask_service;
	
	@Autowired
	private Power_Up_Uplink_Service power_up_service;
	
	@Autowired
	private Recharge_Over_Uplink_Service recharge_over_service;
	
	@Autowired
	private Tamper_Event_Uplink_Service tamper_event_service;
	
	@Autowired
	private Meter_Info_Downlink_Service meter_info_service;
	
	@Autowired
	private Instantaneous_Data_Downlink_Service instant_data_service;
	
	
	private MqttClient client;
	
	private static String broker_address = "";
	
	private static String broker_ip_address = "";
	
	private static String broker_port ="";
	
	private static String user_name = "";
	
	private static char[] api_key;

	private static String topic ="";	
	
	private String message = "";
	
	private String latestMessage = "No message yet";

	private volatile boolean active = true;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


	
@EventListener(ApplicationReadyEvent.class)
public void connect() {
    try {
        Mqtt_Api_Key_Proj dto = mqtt_repo
                .find_all_api_keys(PageRequest.of(0, 1))
                .getContent()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("MQTT config not found"));

        // Get Mqtt broker details from Mqtt table
        user_name = dto.getUserName();
        api_key = dto.getApiKey().toCharArray();
        broker_ip_address = "tcps://" + dto.getBrokerIpAddress();
        broker_port = ":" + dto.getPortNumber();
        broker_address = broker_ip_address + broker_port;
        topic = dto.getTopicName();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setKeepAliveInterval(60);
        options.setConnectionTimeout(30);
        options.setMaxInflight(1000);
        options.setUserName(user_name);
        options.setPassword(api_key);

        client = new MqttClient(broker_address, MqttClient.generateClientId());

        client.setCallback(new MqttCallbackExtended() {
        	
        	 @Override
        	    public void connectComplete(boolean reconnect, String serverURI) {
        	        try {
        	            System.out.println(reconnect 
        	                ? "🔄 MQTT Reconnected to: " + serverURI
        	                : "✅ MQTT Connected to: " + serverURI);

        	            client.subscribe(topic);
        	            System.out.println("📡 Subscribed to: " + topic);

        	        } catch (MqttException e) {
        	            e.printStackTrace();
        	        }
        	    }
        	
        	 @Override			
			  public void connectionLost(Throwable cause) {
			  System.err.println("MQTT Connection lost: " + cause.getMessage()); 
			  }
        	 
			 
			/*
			 * @Override public void connectionLost(Throwable cause) {
			 * System.err.println("❌ MQTT Connection lost: " + cause.getMessage());
			 * 
			 * new Thread(() -> { while (active) { try { Thread.sleep(5000);
			 * 
			 * if (!client.isConnected()) { System.out.println("🔄 Reconnecting MQTT...");
			 * client.connect(options); client.subscribe(topic);
			 * System.out.println("✅ MQTT reconnected & resubscribed"); } break; } catch
			 * (Exception e) { System.err.println("Reconnect failed, retrying..."); } }
			 * }).start(); }
			 */


			/*
			 * @Override public void messageArrived(String topic, MqttMessage msg) { if
			 * (!active) return; message= new String(msg.getPayload()); latestMessage =
			 * message; if (topic.contains("/up")) { processPayload(message); } }
			 */
        	
        	@Override
        	public void messageArrived(String topic, MqttMessage msg) {
        	    if (!active) return;

        	    final String payload = new String(msg.getPayload());
        	    latestMessage = payload;

        	    if (topic.contains("/up")) {
        	      //  new Thread(() -> processPayload(payload)).start();
        	    	CompletableFuture.runAsync(() -> processPayload(payload));
        	    }
        	}


            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {}
        });

        client.connect(options);
        client.subscribe(topic);

        System.out.println("✅ MQTT Connected to: " + broker_address);
        System.out.println("📡 Subscribed to: " + topic);

    } catch (Exception e) {
        e.printStackTrace();
    }
}


	@PreDestroy
	public void cleanup() {
		active = false;
		try {
			if (client != null && client.isConnected()) {
				client.disconnect();
				client.close();
				System.out.println("MQTT client disconnected and closed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLatestMessage() {
		System.out.println("Returning latest message: " + latestMessage);
		return latestMessage;
	}
	
	
	private void processPayload(String json) {
		 try {
		        MQTT_Listner_Receive_Data_Dto uplink = UplinkJsonParser.parse(json);

		        String hex = uplink.getHexPayload();
		        System.out.println(hex);
		        PacketType type = PacketType.resolve(hex);
		        System.out.println(type);
		        switch (type) {
		        
					/* Uplink Data */
		        	case POWER_UP ->{
	            	Power_Up_Uplink_Dto power_dto = PacketDecoderFactory.decode(hex);
	            	power_up_service.save(power_dto,uplink);	            	
		        	}
		            case DAILY_UPDATE -> {
		                Daily_Update_Uplink_Dto daily_dto = PacketDecoderFactory.decode(hex);
		                daily_update_service.save(daily_dto, uplink);
		            }        
		            case TAMPER -> {
		            	Tamper_Event_Uplink_Dto tamper_dto = PacketDecoderFactory.decode(hex);
		            	tamper_event_service.save(tamper_dto,uplink);		            	
		            }		            
		            case GAS_LEAK ->{
		            	Gas_Leak_Uplink_Dto gas_dto = PacketDecoderFactory.decode(hex);
		            	gas_leask_service.save(gas_dto,uplink);
		            }		            
		            case RECHARGE_OVER ->{
		            	Recharge_Over_Uplink_Dto recharge_dto = PacketDecoderFactory.decode(hex);
		            	recharge_over_service.save(recharge_dto,uplink);		            	
		            }		            
		            case MANUAL_DEVICE_EVENT_NOTIFICATION ->{
		            	Device_Event_Notification_Uplink_Dto device_event_dto = PacketDecoderFactory.decode(hex);
		            	device_event_notification_service.save(device_event_dto,uplink);		            	
		            }		            
		            case BATTERY_CUTTOFF ->{
		            	Battery_Cut_Off_Uplink_Dto battery_dto = PacketDecoderFactory.decode(hex);
		            	batter_cut_off_service.save(battery_dto,uplink);		            	
		            }
		            
					/* Downlink Data */
		            case READ_METER_INFO ->{
		            	Meter_Info_Downlink_Dto meter_info_dto = PacketDecoderFactory.decode(hex);
		            	meter_info_service.save(meter_info_dto,uplink);		            	
		            }
		            
		            case READ_INSTANTANEOUS_DATA ->{
		            	Instantaneous_Data_Downlink_Dto instant_data_dto = PacketDecoderFactory.decode(hex);
		            	instant_data_service.save(instant_data_dto,uplink);		            	
		            }
		            
		            
		            
		            default ->{
		            	
		            	
		            }
					
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
			sb.append(String.format("%02X", b));
		return sb.toString();
	}

	
}




