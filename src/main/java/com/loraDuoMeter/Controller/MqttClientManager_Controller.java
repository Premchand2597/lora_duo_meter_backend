package com.loraDuoMeter.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj;

@Component
public class MqttClientManager_Controller {
	
	 private final Map<String, IMqttAsyncClient> clients = new ConcurrentHashMap<>();

	    public IMqttAsyncClient getClient(Mqtt_Api_Key_Down_Proj cfg) {
	        return clients.computeIfAbsent(cfg.getGatewayId(), g -> createClient(cfg));
	    }

	    private IMqttAsyncClient createClient(Mqtt_Api_Key_Down_Proj cfg) {
	        try {
	            String brokerUrl = "tcp://" + cfg.getBrokerIpAddress() + ":" + cfg.getPortNumber();
	            IMqttAsyncClient client = new MqttAsyncClient(brokerUrl, "gw-" + cfg.getGatewayId());

	            MqttConnectOptions options = new MqttConnectOptions();
	            options.setUserName(cfg.getUserName());
	            options.setPassword(cfg.getApiKey().toCharArray());
	            options.setAutomaticReconnect(true);
	            options.setCleanSession(false);

	            client.connect(options).waitForCompletion();
	            return client;

	        } catch (MqttException e) {
	            throw new RuntimeException("Failed to connect MQTT broker", e);
	        }
	    }
}
