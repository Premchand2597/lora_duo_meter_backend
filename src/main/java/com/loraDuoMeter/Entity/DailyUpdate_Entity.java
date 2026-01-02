package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "daily_update_table")
public class DailyUpdate_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private long slNo;
	@Column(name = "battery_voltage")
	private String batteryVoltage;
	@Column(name = "device_rtc")
	private String deviceRtc;
	@Column(name = "pulse_count")
	private String pulseCount;
	@Column(name = "valve_status")
	private String valveStatus;
	
	
	
	
	
	/*@Column(name = "reserved_byte")
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
	private String devEui;
	@Column(name = "join_eui")
	private String joinEui;
	@Column(name = "device_address")
	private String deviceAddress;
	@Column(name = "received_at")
	private String receivedAt;
	@Column(name = "f_port")
	private int fPort;
	@Column(name = "f_cnt")
	private int fCnt;
	private String payload;
	private String port;
	@Column(name = "gateway_id")
	private String gatewayId;
	@Column(name = "gateway_eui")
	private String gatewayEui;
	private String rssi;
	@Column(name = "channel_rssi")
	private String channelRssi;
	private String snr;
	private String bandwidth;
	@Column(name = "spreading_factor")
	private String spreadingFactor;
	private String frequency;
	@Column(name = "net_id")
	private String netId;
	@Column(name = "command_id")
	private String commandId;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_eui", referencedColumnName = "meter_sl_no", updatable = false, insertable = false)
	private MeterDetails_Entity meterDetails;

	public long getSlNo() {
		return slNo;
	}
	public void setSlNo(long slNo) {
		this.slNo = slNo;
	}
	public String getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
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
	public String getValveStatus() {
		return valveStatus;
	}
	public void setValveStatus(String valveStatus) {
		this.valveStatus = valveStatus;
	}
	public MeterDetails_Entity getMeterDetails() {
		return meterDetails;
	}
	public void setMeterDetails(MeterDetails_Entity meterDetails) {
		this.meterDetails = meterDetails;
	}
	@Override
	public String toString() {
		return "DailyUpdate_Entity [slNo=" + slNo + ", batteryVoltage=" + batteryVoltage + ", deviceRtc=" + deviceRtc
				+ ", pulseCount=" + pulseCount + ", valveStatus=" + valveStatus + ", meterDetails=" + meterDetails
				+ "]";
	}
	
	
	/*public long getSlNo() {
		return slNo;
	}
	public void setSlNo(long slNo) {
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
	public int getfPort() {
		return fPort;
	}
	public void setfPort(int fPort) {
		this.fPort = fPort;
	}
	public int getfCnt() {
		return fCnt;
	}
	public void setfCnt(int fCnt) {
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
	public MeterDetails_Entity getMeterDetails() {
		return meterDetails;
	}
	public void setMeterDetails(MeterDetails_Entity meterDetails) {
		this.meterDetails = meterDetails;
	}
	@Override
	public String toString() {
		return "DailyUpdate_Entity [slNo=" + slNo + ", commandId=" + commandId + ", reservedByte=" + reservedByte
				+ ", networkType=" + networkType + ", deviceType=" + deviceType + ", meterModel=" + meterModel
				+ ", firmwareVersion=" + firmwareVersion + ", meterConnection=" + meterConnection + ", valveStatus="
				+ valveStatus + ", deviceRtc=" + deviceRtc + ", pulseCount=" + pulseCount + ", batteryVoltage="
				+ batteryVoltage + ", nextUpdateTime=" + nextUpdateTime + ", pendingEvent=" + pendingEvent
				+ ", rechargeStatus=" + rechargeStatus + ", lastUpdateTime=" + lastUpdateTime + ", deviceId=" + deviceId
				+ ", applicationId=" + applicationId + ", devEui=" + devEui + ", joinEui=" + joinEui
				+ ", deviceAddress=" + deviceAddress + ", receivedAt=" + receivedAt + ", fPort=" + fPort + ", fCnt="
				+ fCnt + ", payload=" + payload + ", port=" + port + ", gatewayId=" + gatewayId + ", gatewayEui="
				+ gatewayEui + ", rssi=" + rssi + ", channelRssi=" + channelRssi + ", snr=" + snr + ", bandwidth="
				+ bandwidth + ", spreadingFactor=" + spreadingFactor + ", frequency=" + frequency + ", netId=" + netId
				+ ", meterDetails=" + meterDetails + "]";
	}*/
	
	
	
}
