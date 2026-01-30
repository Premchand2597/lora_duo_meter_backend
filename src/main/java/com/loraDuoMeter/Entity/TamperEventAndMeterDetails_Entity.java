package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TamperEventAndMeterDetails_Entity {
	@Id
	@Column(name = "sl_no")
	private long slNo;
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
	
	@Column(name = "meter_details_meter_sl_no")
	private String meterDetailsMeterSlNo;
	@Column(name = "replaced_meter_sl_no")
	private String replacedMeterSlNo;
	@Column(name = "tamper_detail")
	private String tamperDetail;
	@Column(name = "replace_reason")
	private String replaceReason;
	@Column(name = "building_name")
	private String buildingName;
	@Column(name = "resident_type")
	private String residentType;
	@Column(name = "resident_name")
	private String residentName;
	@Column(name = "meter_details_device_type")
	private String meterDetailsDeviceType;
	@Column(name = "meter_details_meter_connection_type")
	private String meterDetailsMeterConnectionType;
	@Column(name = "meter_details_date_time")
	private String meterDetailsDateTime;
	
	public long getSlNo() {
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
	public String getMeterDetailsMeterSlNo() {
		return meterDetailsMeterSlNo;
	}
	public void setMeterDetailsMeterSlNo(String meterDetailsMeterSlNo) {
		this.meterDetailsMeterSlNo = meterDetailsMeterSlNo;
	}
	public String getReplacedMeterSlNo() {
		return replacedMeterSlNo;
	}
	public void setReplacedMeterSlNo(String replacedMeterSlNo) {
		this.replacedMeterSlNo = replacedMeterSlNo;
	}
	public String getTamperDetail() {
		return tamperDetail;
	}
	public void setTamperDetail(String tamperDetail) {
		this.tamperDetail = tamperDetail;
	}
	public String getReplaceReason() {
		return replaceReason;
	}
	public void setReplaceReason(String replaceReason) {
		this.replaceReason = replaceReason;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getResidentType() {
		return residentType;
	}
	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
	public String getMeterDetailsDeviceType() {
		return meterDetailsDeviceType;
	}
	public void setMeterDetailsDeviceType(String meterDetailsDeviceType) {
		this.meterDetailsDeviceType = meterDetailsDeviceType;
	}
	public String getMeterDetailsMeterConnectionType() {
		return meterDetailsMeterConnectionType;
	}
	public void setMeterDetailsMeterConnectionType(String meterDetailsMeterConnectionType) {
		this.meterDetailsMeterConnectionType = meterDetailsMeterConnectionType;
	}
	public String getMeterDetailsDateTime() {
		return meterDetailsDateTime;
	}
	public void setMeterDetailsDateTime(String meterDetailsDateTime) {
		this.meterDetailsDateTime = meterDetailsDateTime;
	}
	@Override
	public String toString() {
		return "TamperEventAndMeterDetails_Entity [slNo=" + slNo + ", commandId=" + commandId + ", reservedByte="
				+ reservedByte + ", networkType=" + networkType + ", deviceType=" + deviceType + ", meterModel="
				+ meterModel + ", firmwareVersion=" + firmwareVersion + ", meterConnectionType=" + meterConnectionType
				+ ", valveStatus=" + valveStatus + ", deviceRtc=" + deviceRtc + ", pulseCount=" + pulseCount
				+ ", batteryVoltage=" + batteryVoltage + ", nextUpdateTime=" + nextUpdateTime + ", tamperType="
				+ tamperType + ", lastUpdateTime=" + lastUpdateTime + ", deviceId=" + deviceId + ", applicationId="
				+ applicationId + ", devEui=" + devEui + ", joinEui=" + joinEui + ", deviceAddress=" + deviceAddress
				+ ", receivedAt=" + receivedAt + ", fPort=" + fPort + ", fCnt=" + fCnt + ", payload=" + payload
				+ ", port=" + port + ", gatewayId=" + gatewayId + ", gatewayEui=" + gatewayEui + ", rssi=" + rssi
				+ ", channelRssi=" + channelRssi + ", snr=" + snr + ", bandwidth=" + bandwidth + ", spreadingFactor="
				+ spreadingFactor + ", frequency=" + frequency + ", netId=" + netId + ", meterDetailsMeterSlNo="
				+ meterDetailsMeterSlNo + ", replacedMeterSlNo=" + replacedMeterSlNo + ", tamperDetail=" + tamperDetail
				+ ", replaceReason=" + replaceReason + ", buildingName=" + buildingName + ", residentType="
				+ residentType + ", residentName=" + residentName + ", meterDetailsDeviceType=" + meterDetailsDeviceType
				+ ", meterDetailsMeterConnectionType=" + meterDetailsMeterConnectionType + ", meterDetailsDateTime="
				+ meterDetailsDateTime + "]";
	}
	
}
