package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tamper_event_table", schema = "public")

public class TamperEventEntity {

    public TamperEventEntity() {
		super();
	}

	public TamperEventEntity(Integer slNo, String deviceType, String meterModel, String firmwareVersion,
			String meterConnection, String valveStatus, String deviceRtc, String pulseCount, String batteryVoltage,
			String nextUpdateTime, String tamperType, String lastUpdateTime, String deviceId, String applicationId,
			String devEui, String joinEui, String deviceAddress, String receivedAt, String fPort, String fCnt,
			String payload, String port) {
		super();
		this.slNo = slNo;
		this.deviceType = deviceType;
		this.meterModel = meterModel;
		this.firmwareVersion = firmwareVersion;
		this.meterConnection = meterConnection;
		this.valveStatus = valveStatus;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.batteryVoltage = batteryVoltage;
		this.nextUpdateTime = nextUpdateTime;
		this.tamperType = tamperType;
		this.lastUpdateTime = lastUpdateTime;
		this.deviceId = deviceId;
		this.applicationId = applicationId;
		this.devEui = devEui;
		this.joinEui = joinEui;
		this.deviceAddress = deviceAddress;
		this.receivedAt = receivedAt;
		this.fPort = fPort;
		this.fCnt = fCnt;
		this.payload = payload;
		this.port = port;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Integer slNo;

    // --- COLUMNS FROM device_type TO port ---

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "meter_model")
    private String meterModel;

    @Column(name = "firmware_version") 
    private String firmwareVersion;

    @Column(name = "meter_connection_type") 
    private String meterConnection;

    @Column(name = "valve_status")
    private String valveStatus;

    @Column(name = "device_rtc")
    private String deviceRtc;

    @Column(name = "pulse_count")
    private String pulseCount;

    @Column(name = "battery_voltage")
    private String batteryVoltage;

    // FIXED: Changed from 'next_update_tim' to 'next_update_time'
    @Column(name = "next_update_time") 
    private String nextUpdateTime;

    @Column(name = "tamper_type")
    private String tamperType;

    @Column(name = "last_update_time") 
    private String lastUpdateTime;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "application_id")
    private String applicationId;

    @Column(name = "dev_eui")
    private String devEui;

    @Column(name = "join_eui")
    private String joinEui;

    @Column(name = "device_address")
    private String deviceAddress;

    @Column(name = "received_at")
    private String receivedAt;

    @Column(name = "f_port")
    private String fPort;

    @Column(name = "f_cnt")
    private String fCnt;

    @Column(name = "payload")
    private String payload;

    @Column(name = "port")
    private String port;

	public Integer getSlNo() {
		return slNo;
	}

	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
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

	public String getMeterConnection() {
		return meterConnection;
	}

	public void setMeterConnection(String meterConnection) {
		this.meterConnection = meterConnection;
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

	public String getTamperType() {
		return tamperType;
	}

	public void setTamperType(String tamperType) {
		this.tamperType = tamperType;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getDevEui() {
		return devEui;
	}

	public void setDevEui(String devEui) {
		this.devEui = devEui;
	}

	public String getJoinEui() {
		return joinEui;
	}

	public void setJoinEui(String joinEui) {
		this.joinEui = joinEui;
	}

	public String getDeviceAddress() {
		return deviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	public String getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getfPort() {
		return fPort;
	}

	public void setfPort(String fPort) {
		this.fPort = fPort;
	}

	public String getfCnt() {
		return fCnt;
	}

	public void setfCnt(String fCnt) {
		this.fCnt = fCnt;
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
}