package com.loraDuoMeter.Downlink_Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.loraDuoMeter.DTO.MQTT_Listner_Receive_Data_Dto;
import com.loraDuoMeter.Downlink_DTO.Meter_Info_Downlink_Dto;

import jakarta.annotation.PostConstruct;

@Service
public class Meter_Info_Downlink_Service {
	
	
	 private final JdbcTemplate jdbcTemplate;
	    
	    public Meter_Info_Downlink_Service(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	    private static final String INSERT_SQL =
	        "INSERT INTO odm_meter_info_table (" +
	        "command_id, reserved_byte, network_type, device_type, meter_model, firmware_version, " +
	        "meter_connection_type, valve_status, device_rtc, pulse_count, battery_voltage, " +
	        "pending_event, recharge_status, credit_limit_01, credit_limit_02, leak_detector_1, leak_detector_2, per_litre_price, available_amount, battery_voltage_cutt_off, " +
	        "device_id, application_id, dev_eui, join_eui, device_address, received_at, " +
	        "f_port, f_cnt, payload, port, " +
	        "gateway_id, gateway_eui, rssi, channel_rssi, snr, bandwidth, " +
	        "spreading_factor, frequency, net_id" +
	        ") VALUES (" +
	        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
	        ")";

	    private final BlockingQueue<Object[]> queue =
	            new LinkedBlockingQueue<>(100_000);

	    // Called from MQTT listener
	    public void save(Meter_Info_Downlink_Dto packet,
	                     MQTT_Listner_Receive_Data_Dto uplink) {

	        Object[] row = toSqlArgs(packet, uplink);

	        if (!queue.offer(row)) {
	            System.err.println("❌ Queue full – dropping packet");
	        }
	    }

	    // 🔥 SINGLE DB WRITER THREAD
	    @PostConstruct
	    public void startDbWriter() {
	        Thread dbThread = new Thread(() -> {
	            List<Object[]> batch = new ArrayList<>(500);

	            while (true) {
	                try {
	                    // wait for at least one record
	                    batch.add(queue.take());
	                    queue.drainTo(batch, 499);

	                    jdbcTemplate.batchUpdate(INSERT_SQL, batch);
	                    System.out.println("✅ Inserted " + batch.size());

	                    batch.clear();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        dbThread.setName("daily-uplink-db-writer");
	        dbThread.setDaemon(true);
	        dbThread.start();
	    }

	    private Object[] toSqlArgs(Meter_Info_Downlink_Dto packet, MQTT_Listner_Receive_Data_Dto uplink) {

	        return new Object[]{
	            packet.getCommand_id(), packet.getReserved_byte(), packet.getNetwork_type(),
	            packet.getDevice_type(), packet.getMeter_model(), packet.getFirmware_version(),
	            packet.getMeter_connection(), packet.getValve_status(), packet.getDevice_rtc(),
	            packet.getPulse_count(), packet.getBattery_voltage(),
	            packet.getPending_event(), packet.getRecharge_status(),packet.getCredit_limits_01(),packet.getCredit_limits_02(),packet.getLeak_detector_1(),packet.getLeak_detector_2(),packet.getGas_per_litre(),packet.getAvailable_amount(),packet.getBattery_voltage_cutt_off(),

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