package com.loraDuoMeter.Downlink_DTO;

public class Meter_Info_Downlink_Dto {
	
	private String sl_no;
	
	private String command_id;
	
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
	
	private String pending_event;	
	
	private String recharge_status;
	
	private String credit_limits_01;
	
	private String credit_limits_02;
	
	private String leak_detector_1;
	
	private String leak_detector_2;
	
	private String gas_per_litre;
	
	private String available_amount;
	
	private String battery_voltage_cutt_off;
	
	private String last_update_time;

	public String getSl_no() {
		return sl_no;
	}

	public void setSl_no(String sl_no) {
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
	
	

	public String getCredit_limits_01() {
		return credit_limits_01;
	}

	public void setCredit_limits_01(String credit_limits_01) {
		this.credit_limits_01 = credit_limits_01;
	}

	public String getCredit_limits_02() {
		return credit_limits_02;
	}

	public void setCredit_limits_02(String credit_limits_02) {
		this.credit_limits_02 = credit_limits_02;
	}

	public String getLeak_detector_1() {
		return leak_detector_1;
	}

	public void setLeak_detector_1(String leak_detector_1) {
		this.leak_detector_1 = leak_detector_1;
	}

	public String getLeak_detector_2() {
		return leak_detector_2;
	}

	public void setLeak_detector_2(String leak_detector_2) {
		this.leak_detector_2 = leak_detector_2;
	}

	public String getGas_per_litre() {
		return gas_per_litre;
	}

	public void setGas_per_litre(String gas_per_litre) {
		this.gas_per_litre = gas_per_litre;
	}

	public String getAvailable_amount() {
		return available_amount;
	}

	public void setAvailable_amount(String available_amount) {
		this.available_amount = available_amount;
	}

	public String getBattery_voltage_cutt_off() {
		return battery_voltage_cutt_off;
	}

	public void setBattery_voltage_cutt_off(String battery_voltage_cutt_off) {
		this.battery_voltage_cutt_off = battery_voltage_cutt_off;
	}

	

	public Meter_Info_Downlink_Dto(String sl_no, String command_id, String reserved_byte, String network_type,
			String device_type, String meter_model, String firmware_version, String meter_connection,
			String valve_status, String device_rtc, String pulse_count, String battery_voltage, String pending_event,
			String recharge_status, String credit_limits_01, String credit_limits_02, String leak_detector_1,
			String leak_detector_2, String gas_per_litre, String available_amount, String battery_voltage_cutt_off,
			String last_update_time) {
		super();
		this.sl_no = sl_no;
		this.command_id = command_id;
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
		this.pending_event = pending_event;
		this.recharge_status = recharge_status;
		this.credit_limits_01 = credit_limits_01;
		this.credit_limits_02 = credit_limits_02;
		this.leak_detector_1 = leak_detector_1;
		this.leak_detector_2 = leak_detector_2;
		this.gas_per_litre = gas_per_litre;
		this.available_amount = available_amount;
		this.battery_voltage_cutt_off = battery_voltage_cutt_off;
		this.last_update_time = last_update_time;
	}

	public Meter_Info_Downlink_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
