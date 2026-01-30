package com.loraDuoMeter.Uplink_Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.loraDuoMeter.DTO.MQTT_Listner_Receive_Data_Dto;
import com.loraDuoMeter.Uplink_DTO.Power_Up_Uplink_Dto;

import jakarta.annotation.PostConstruct;

@Service
public class Power_Up_Uplink_Service {

	 private final JdbcTemplate jdbcTemplate;
	    
	    public Power_Up_Uplink_Service(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	    private static final String INSERT_SQL =
	        "INSERT INTO power_up_table (" +
	        "command_id, reserved_byte, network_type, device_type, meter_model, firmware_version, " +
	        "meter_connection_type, valve_status, device_rtc, pulse_count, battery_voltage, " +
	        "next_update_time," +
	        "device_id, application_id, dev_eui, join_eui, device_address, received_at, " +
	        "f_port, f_cnt, payload, port, " +
	        "gateway_id, gateway_eui, rssi, channel_rssi, snr, bandwidth, " +
	        "spreading_factor, frequency, net_id" +
	        ") VALUES (" +
	        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
	        ")";
	    
	    private static final String INSERT_METER_DETAILS_SQL =
	    	    "INSERT INTO meter_details (" +
	    	    "meter_sl_no,meter_mac_address, meter_model, device_type, network_type, meter_connection_type,valve_status,battery_voltage,device_rtc,pulse_count,gateway_id" +
	    	    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
	    	    "ON CONFLICT (meter_sl_no) DO NOTHING";
	    
	    private static final String INSERT_GATEWAY_DETAILS_SQL =
	    	    "INSERT INTO gateway_details (" +
	    	    "gateway_id,rssi, channel_rssi, bandwidth, frequency,net_id" +
	    	    ") VALUES (?, ?, ?, ?, ?, ?) " +
	    	    "ON CONFLICT (gateway_id) DO NOTHING";
	    


	    private final BlockingQueue<Object[]> queue =
	            new LinkedBlockingQueue<>(100_000);

	    // Called from MQTT listener
	    public void save(Power_Up_Uplink_Dto packet,
	                     MQTT_Listner_Receive_Data_Dto uplink) {

	        Object[] row = toSqlArgs(packet, uplink);

	        if (!queue.offer(row)) {
	            System.err.println("❌ Queue full – dropping packet");
	        }
	    }

		/*
		 * // 🔥 SINGLE DB WRITER THREAD
		 * 
		 * @PostConstruct public void startDbWriter() { Thread dbThread = new Thread(()
		 * -> { List<Object[]> batch = new ArrayList<>(500);
		 * 
		 * while (true) { try { // wait for at least one record batch.add(queue.take());
		 * queue.drainTo(batch, 499);
		 * 
		 * jdbcTemplate.batchUpdate(INSERT_SQL, batch); System.out.println("✅ Inserted "
		 * + batch.size());
		 * 
		 * batch.clear(); } catch (Exception e) { e.printStackTrace(); } } });
		 * 
		 * dbThread.setName("daily-uplink-db-writer"); dbThread.setDaemon(true);
		 * dbThread.start(); }
		 */
	    
	    @PostConstruct
	    public void startDbWriter() {
	        Thread dbThread = new Thread(() -> {
	            List<Object[]> uplinkBatch = new ArrayList<>(500);
	            List<Object[]> meterBatch = new ArrayList<>(500);
	            List<Object[]> gatewayBatch = new ArrayList<>(500);

	            while (true) {
	                try {
	                    Object[] row = queue.take();
	                    uplinkBatch.add(row);
	                    queue.drainTo(uplinkBatch, 499);

	                    for (Object[] r : uplinkBatch) {
	                        meterBatch.add(new Object[]{
	                            r[14],// devEui
	                            r[14],
	                            r[4],  // meter_model
	                            r[3],  // device_type
	                            r[2],  // network_type
	                            r[6],  // meter_connection_type
	                            r[7],
	                            r[10],
	                            r[8],
	                            r[9],
	                            r[23]
	                        });

							
							  gatewayBatch.add(new Object[]{ 
									  r[23], // gateway_eui
									  r[24],
									  r[25],									 
									  r[27],
									  r[29],
									  r[30]
							   });
							 
	                    }

	                    jdbcTemplate.batchUpdate(INSERT_METER_DETAILS_SQL, meterBatch);
						jdbcTemplate.batchUpdate(INSERT_GATEWAY_DETAILS_SQL, gatewayBatch); 
	                    jdbcTemplate.batchUpdate(INSERT_SQL, uplinkBatch);

	                    meterBatch.clear();
	                    gatewayBatch.clear();
	                    uplinkBatch.clear();

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        dbThread.setDaemon(true);
	        dbThread.start();
	    }


	    private Object[] toSqlArgs(Power_Up_Uplink_Dto packet,
	                              MQTT_Listner_Receive_Data_Dto uplink) {

	        return new Object[]{
	            packet.getCommand_id(), packet.getReserved_byte(), packet.getNetwork_type(),
	            packet.getDevice_type(), packet.getMeter_model(), packet.getFirmware_version(), 
	            packet.getMeter_connection_type(), packet.getValve_status(), packet.getDevice_rtc(),
	            packet.getPulse_count(), packet.getBattery_voltage(),
	            packet.getNext_update_time(),

	            uplink.getDeviceId(), uplink.getApplicationId(), uplink.getDevEui(),
	            uplink.getJoinEui(), uplink.getDevAddr(), uplink.getReceivedAt(),
	            uplink.getfPort(), uplink.getfCnt(), uplink.getHexPayload(),
	            String.valueOf(uplink.getfPort()),

	            uplink.getGatewayId(), uplink.getGatewayEui(),
	            String.valueOf(uplink.getRssi()),
	            String.valueOf(uplink.getChannelRssi()),
	            String.valueOf(uplink.getSnr()),
	            String.valueOf(uplink.getBandwidth()),
	            String.valueOf(uplink.getSpreadingFactor()),
	            uplink.getFrequency(),
	            uplink.getNetId()
	        };
	    }

	
}
