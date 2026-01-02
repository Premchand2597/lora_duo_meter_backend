package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "daily_update_table")
public class DailyUpdateEntity {

	public DailyUpdateEntity() {
		super();
	}

	public DailyUpdateEntity(Long id, String commandId, String reservedByte, String networkType, String deviceType,
			String meterModel, String firmwareVersion, String meterConnection, String valveStatus, String deviceRtc,
			String pulseCount, String batteryVoltage, String nextUpdateTime, String pendingEvent, String rechargeStatus,
			String lastUpdateTime, String deviceId, String applicationId, String devEui, String joinEui,
			String deviceAddress, String receivedAt, String fPort, String fCnt, String payload, String port,
			String gatewayId, String gatewayEui, String rssi, String channelRssi, String snr, String bandwidth,
			String spreadingFactor, String frequency, String netId) {
		super();
		this.id = id;
		this.commandId = commandId;
		this.reservedByte = reservedByte;
		this.networkType = networkType;
		this.deviceType = deviceType;
		this.meterModel = meterModel;
		this.firmwareVersion = firmwareVersion;
		this.meterConnection = meterConnection;
		this.valveStatus = valveStatus;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.batteryVoltage = batteryVoltage;
		this.nextUpdateTime = nextUpdateTime;
		this.pendingEvent = pendingEvent;
		this.rechargeStatus = rechargeStatus;
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
		this.gatewayId = gatewayId;
		this.gatewayEui = gatewayEui;
		this.rssi = rssi;
		this.channelRssi = channelRssi;
		this.snr = snr;
		this.bandwidth = bandwidth;
		this.spreadingFactor = spreadingFactor;
		this.frequency = frequency;
		this.netId = netId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Long id; // Changed to Long for auto-increment efficiency, mapped to sl_no

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

    @Column(name = "meter_connection")
    private String meterConnection;

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

    @Column(name = "pending_event")
    private String pendingEvent;

    @Column(name = "recharge_status")
    private String rechargeStatus;

    @Column(name = "last_update_time")
    private String lastUpdateTime;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "application_id")
    private String applicationId;

    @Column(name = "dev_eui")
    private String devEui; // This is the key column to match with Meter Serial No

    @Column(name = "join_eui")
    private String joinEui;

    @Column(name = "device_address")
    private String deviceAddress;

    @Column(name = "received_at")
    private String receivedAt; // We will use this to check "Today's Date"

    @Column(name = "f_port")
    private String fPort;

    @Column(name = "f_cnt")
    private String fCnt;

    @Column(name = "payload")
    private String payload;

    @Column(name = "port")
    private String port;

    @Column(name = "gateway_id")
    private String gatewayId;

    @Column(name = "gateway_eui")
    private String gatewayEui;

    @Column(name = "rssi")
    private String rssi;

    @Column(name = "channel_rssi")
    private String channelRssi;

    @Column(name = "snr")
    private String snr;

    @Column(name = "bandwidth")
    private String bandwidth;

    @Column(name = "spreading_factor")
    private String spreadingFactor;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "net_id")
    private String netId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPendingEvent() {
		return pendingEvent;
	}

	public void setPendingEvent(String pendingEvent) {
		this.pendingEvent = pendingEvent;
	}

	public String getRechargeStatus() {
		return rechargeStatus;
	}

	public void setRechargeStatus(String rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
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

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getGatewayEui() {
		return gatewayEui;
	}

	public void setGatewayEui(String gatewayEui) {
		this.gatewayEui = gatewayEui;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getChannelRssi() {
		return channelRssi;
	}

	public void setChannelRssi(String channelRssi) {
		this.channelRssi = channelRssi;
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

	public String getSpreadingFactor() {
		return spreadingFactor;
	}

	public void setSpreadingFactor(String spreadingFactor) {
		this.spreadingFactor = spreadingFactor;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}
	
}
