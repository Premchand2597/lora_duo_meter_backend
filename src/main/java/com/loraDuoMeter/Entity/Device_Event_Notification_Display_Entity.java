package com.loraDuoMeter.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "device_event_notification_table")
public class Device_Event_Notification_Display_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sl_no;
	
	private String command_id;
	
	private String reserved_byte;
	
	private String network_type;
	
	private String device_type;
	
	private String meter_model;
	
	private String firmware_version;	
	
	private String meter_connection_type;
	
	private String valve_status;
	
	private String device_rtc;
	
	private String pulse_count;
	
	private String battery_voltage;
	
	private String next_update_time;
	
	private String pending_event;
	
	private String recharge_status;
	
	private String last_update_time;
	
	private String device_id;
	
	private String application_id;
	
	private String dev_eui;
	
	private String join_eui;
	
	private String device_address;	
	
	private String received_at;
	
	private String f_port;
	
	private String f_cnt;
	
	private String payload;
	
	private String port;
	
	private String gateway_id;
	
	private String gateway_eui;	
	
	private String rssi;
	
	private String channel_rssi;
	
	private String snr;
	
	private String bandwidth;
	
	private String spreading_factor;
	
	private String frequency;
	
	private String net_id;

	public Long getSl_no() {
		return sl_no;
	}

	public void setSl_no(Long sl_no) {
		this.sl_no = sl_no;
	}

	public String getCommand_id() {
		return command_id;
	}

	public void setCommand_id(String command_id) {
		this.command_id = command_id;
	}

	public String getReserved_byte() {
		return reserved_byte;
	}

	public void setReserved_byte(String reserved_byte) {
		this.reserved_byte = reserved_byte;
	}

	public String getNetwork_type() {
		return network_type;
	}

	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getMeter_model() {
		return meter_model;
	}

	public void setMeter_model(String meter_model) {
		this.meter_model = meter_model;
	}

	public String getFirmware_version() {
		return firmware_version;
	}

	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}

	public String getMeter_connection_type() {
		return meter_connection_type;
	}

	public void setMeter_connection_type(String meter_connection_type) {
		this.meter_connection_type = meter_connection_type;
	}

	public String getValve_status() {
		return valve_status;
	}

	public void setValve_status(String valve_status) {
		this.valve_status = valve_status;
	}

	public String getDevice_rtc() {
		return device_rtc;
	}

	public void setDevice_rtc(String device_rtc) {
		this.device_rtc = device_rtc;
	}

	public String getPulse_count() {
		return pulse_count;
	}

	public void setPulse_count(String pulse_count) {
		this.pulse_count = pulse_count;
	}

	public String getBattery_voltage() {
		return battery_voltage;
	}

	public void setBattery_voltage(String battery_voltage) {
		this.battery_voltage = battery_voltage;
	}

	public String getNext_update_time() {
		return next_update_time;
	}

	public void setNext_update_time(String next_update_time) {
		this.next_update_time = next_update_time;
	}

	public String getPending_event() {
		return pending_event;
	}

	public void setPending_event(String pending_event) {
		this.pending_event = pending_event;
	}

	public String getRecharge_status() {
		return recharge_status;
	}

	public void setRecharge_status(String recharge_status) {
		this.recharge_status = recharge_status;
	}

	public String getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getDev_eui() {
		return dev_eui;
	}

	public void setDev_eui(String dev_eui) {
		this.dev_eui = dev_eui;
	}

	public String getJoin_eui() {
		return join_eui;
	}

	public void setJoin_eui(String join_eui) {
		this.join_eui = join_eui;
	}

	public String getDevice_address() {
		return device_address;
	}

	public void setDevice_address(String device_address) {
		this.device_address = device_address;
	}

	public String getReceived_at() {
		return received_at;
	}

	public void setReceived_at(String received_at) {
		this.received_at = received_at;
	}

	public String getF_port() {
		return f_port;
	}

	public void setF_port(String f_port) {
		this.f_port = f_port;
	}

	public String getF_cnt() {
		return f_cnt;
	}

	public void setF_cnt(String f_cnt) {
		this.f_cnt = f_cnt;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(String gateway_id) {
		this.gateway_id = gateway_id;
	}

	public String getGateway_eui() {
		return gateway_eui;
	}

	public void setGateway_eui(String gateway_eui) {
		this.gateway_eui = gateway_eui;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getChannel_rssi() {
		return channel_rssi;
	}

	public void setChannel_rssi(String channel_rssi) {
		this.channel_rssi = channel_rssi;
	}

	public String getSnr() {
		return snr;
	}

	public void setSnr(String snr) {
		this.snr = snr;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getSpreading_factor() {
		return spreading_factor;
	}

	public void setSpreading_factor(String spreading_factor) {
		this.spreading_factor = spreading_factor;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getNet_id() {
		return net_id;
	}

	public void setNet_id(String net_id) {
		this.net_id = net_id;
	}

	public Device_Event_Notification_Display_Entity(Long sl_no, String command_id, String reserved_byte, String network_type,
			String device_type, String meter_model, String firmware_version, String meter_connection_type,
			String valve_status, String device_rtc, String pulse_count, String battery_voltage, String next_update_time,
			String pending_event, String recharge_status, String last_update_time, String device_id,
			String application_id, String dev_eui, String join_eui, String device_address, String received_at,
			String f_port, String f_cnt, String payload, String port, String gateway_id, String gateway_eui,
			String rssi, String channel_rssi, String snr, String bandwidth, String spreading_factor, String frequency,
			String net_id) {
		super();
		this.sl_no = sl_no;
		this.command_id = command_id;
		this.reserved_byte = reserved_byte;
		this.network_type = network_type;
		this.device_type = device_type;
		this.meter_model = meter_model;
		this.firmware_version = firmware_version;
		this.meter_connection_type = meter_connection_type;
		this.valve_status = valve_status;
		this.device_rtc = device_rtc;
		this.pulse_count = pulse_count;
		this.battery_voltage = battery_voltage;
		this.next_update_time = next_update_time;
		this.pending_event = pending_event;
		this.recharge_status = recharge_status;
		this.last_update_time = last_update_time;
		this.device_id = device_id;
		this.application_id = application_id;
		this.dev_eui = dev_eui;
		this.join_eui = join_eui;
		this.device_address = device_address;
		this.received_at = received_at;
		this.f_port = f_port;
		this.f_cnt = f_cnt;
		this.payload = payload;
		this.port = port;
		this.gateway_id = gateway_id;
		this.gateway_eui = gateway_eui;
		this.rssi = rssi;
		this.channel_rssi = channel_rssi;
		this.snr = snr;
		this.bandwidth = bandwidth;
		this.spreading_factor = spreading_factor;
		this.frequency = frequency;
		this.net_id = net_id;
	}

	public Device_Event_Notification_Display_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Device_Event_Notification_Display [sl_no=" + sl_no + ", command_id=" + command_id + ", reserved_byte="
				+ reserved_byte + ", network_type=" + network_type + ", device_type=" + device_type + ", meter_model="
				+ meter_model + ", firmware_version=" + firmware_version + ", meter_connection_type="
				+ meter_connection_type + ", valve_status=" + valve_status + ", device_rtc=" + device_rtc
				+ ", pulse_count=" + pulse_count + ", battery_voltage=" + battery_voltage + ", next_update_time="
				+ next_update_time + ", pending_event=" + pending_event + ", recharge_status=" + recharge_status
				+ ", last_update_time=" + last_update_time + ", device_id=" + device_id + ", application_id="
				+ application_id + ", dev_eui=" + dev_eui + ", join_eui=" + join_eui + ", device_address="
				+ device_address + ", received_at=" + received_at + ", f_port=" + f_port + ", f_cnt=" + f_cnt
				+ ", payload=" + payload + ", port=" + port + ", gateway_id=" + gateway_id + ", gateway_eui="
				+ gateway_eui + ", rssi=" + rssi + ", channel_rssi=" + channel_rssi + ", snr=" + snr + ", bandwidth="
				+ bandwidth + ", spreading_factor=" + spreading_factor + ", frequency=" + frequency + ", net_id="
				+ net_id + "]";
	}
	
	
	

}
