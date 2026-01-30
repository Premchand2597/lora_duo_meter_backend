package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "odm_instantaneous_data_read_table")
@Data
public class OdmInstantaneousEntity {

    public OdmInstantaneousEntity() {
		super();
	}

	public OdmInstantaneousEntity(Long slNo, String commandId, String reservedByte, String networkType,
			String deviceType, String meterModel, String firmwareVersion, String meterConnectionType,
			String valveStatus, String deviceRtc, String pulseCount, String batteryVoltage, String nextUpdateTime,
			String lastUpdateTime) {
		super();
		this.slNo = slNo;
		this.commandId = commandId;
		this.reservedByte = reservedByte;
		this.networkType = networkType;
		this.deviceType = deviceType;
		this.meterModel = meterModel;
		this.firmwareVersion = firmwareVersion;
		this.meterConnectionType = meterConnectionType;
		this.valveStatus = valveStatus;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.batteryVoltage = batteryVoltage;
		this.nextUpdateTime = nextUpdateTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Long slNo;

    @Column(name = "command_id")
    private String commandId;

    @Column(name = "reserved_byte")
    private String reservedByte;

    @Column(name = "network_type")
    private String networkType;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "meter_model")
    private String meterModel;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "meter_connection_type")
    private String meterConnectionType;

    @Column(name = "valve_status")
    private String valveStatus;

    @Column(name = "device_rtc")
    private String deviceRtc;

    @Column(name = "pulse_count")
    private String pulseCount;

    @Column(name = "battery_voltage")
    private String batteryVoltage;

    @Column(name = "next_update_time")
    private String nextUpdateTime;

    @Column(name = "last_update_time")
    private String lastUpdateTime;

	public Long getSlNo() {
		return slNo;
	}

	public void setSlNo(Long slNo) {
		this.slNo = slNo;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getReservedByte() {
		return reservedByte;
	}

	public void setReservedByte(String reservedByte) {
		this.reservedByte = reservedByte;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getMeterModel() {
		return meterModel;
	}

	public void setMeterModel(String meterModel) {
		this.meterModel = meterModel;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getMeterConnectionType() {
		return meterConnectionType;
	}

	public void setMeterConnectionType(String meterConnectionType) {
		this.meterConnectionType = meterConnectionType;
	}

	public String getValveStatus() {
		return valveStatus;
	}

	public void setValveStatus(String valveStatus) {
		this.valveStatus = valveStatus;
	}

	public String getDeviceRtc() {
		return deviceRtc;
	}

	public void setDeviceRtc(String deviceRtc) {
		this.deviceRtc = deviceRtc;
	}

	public String getPulseCount() {
		return pulseCount;
	}

	public void setPulseCount(String pulseCount) {
		this.pulseCount = pulseCount;
	}

	public String getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public String getNextUpdateTime() {
		return nextUpdateTime;
	}

	public void setNextUpdateTime(String nextUpdateTime) {
		this.nextUpdateTime = nextUpdateTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}