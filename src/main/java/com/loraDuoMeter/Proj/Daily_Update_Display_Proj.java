package com.loraDuoMeter.Proj;

public class Daily_Update_Display_Proj {
	
	private String sl_no;
	
	private String reserved_byte;
	
	private String network_type;
	
	private String device_type;
	
	private String meter_model;
	
	private String firmware_version;	
	
	private String meter_connection;
	
	private String valve_status;
	
	private String device_rtc;
	
	private String pulse_count;
	
	private String battery_voltage;
	
	private String next_update_time;
	
	private String pending_event;	
	
	private String recharge_status;
	
	private String last_update_time;
	
	private String dev_eui;	
	
	private String received_at;
	
	private String f_port;
	
	private String gateway_eui;

	public String getSl_no() {
		return sl_no;
	}

	public void setSl_no(String sl_no) {
		this.sl_no = sl_no;
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

	public String getMeter_connection() {
		return meter_connection;
	}

	public void setMeter_connection(String meter_connection) {
		this.meter_connection = meter_connection;
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

	public String getDev_eui() {
		return dev_eui;
	}

	public void setDev_eui(String dev_eui) {
		this.dev_eui = dev_eui;
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

	public String getGateway_eui() {
		return gateway_eui;
	}

	public void setGateway_eui(String gateway_eui) {
		this.gateway_eui = gateway_eui;
	}

	public Daily_Update_Display_Proj(String sl_no, String reserved_byte, String network_type, String device_type,
			String meter_model, String firmware_version, String meter_connection, String valve_status,
			String device_rtc, String pulse_count, String battery_voltage, String next_update_time,
			String pending_event, String recharge_status, String last_update_time, String dev_eui, String received_at,
			String f_port, String gateway_eui) {
		super();
		this.sl_no = sl_no;
		this.reserved_byte = reserved_byte;
		this.network_type = network_type;
		this.device_type = device_type;
		this.meter_model = meter_model;
		this.firmware_version = firmware_version;
		this.meter_connection = meter_connection;
		this.valve_status = valve_status;
		this.device_rtc = device_rtc;
		this.pulse_count = pulse_count;
		this.battery_voltage = battery_voltage;
		this.next_update_time = next_update_time;
		this.pending_event = pending_event;
		this.recharge_status = recharge_status;
		this.last_update_time = last_update_time;
		this.dev_eui = dev_eui;
		this.received_at = received_at;
		this.f_port = f_port;
		this.gateway_eui = gateway_eui;
	}

	public Daily_Update_Display_Proj() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	
	
	

}
