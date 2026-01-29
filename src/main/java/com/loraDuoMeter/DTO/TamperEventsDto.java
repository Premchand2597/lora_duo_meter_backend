package com.loraDuoMeter.DTO;

public class TamperEventsDto {

	private long slNo;
	private String commandId;
	private String reservedByte;
	private String networkType;
	private String deviceType;
	private String meterModel;
	private String firmwareVersion;
	private String meterConnectionType;
	private String valveStatus;
	private String deviceRtc;
	private String pulseCount;
	private String batteryVoltage;
	private String nextUpdateTime;
	private String tamperType;
	private String lastUpdateTime;
	private String deviceId;
	private String applicationId;
	private String devEui;
	private String joinEui;
	private String deviceAddress;
	private String receivedAt;
	private String fPort;
	private String fCnt;
	private String payload;
	private String port;
	private String gatewayId;
	private String gatewayEui;
	private String rssi;
	private String channelRssi;
	private String snr;
	private String bandwidth;
	private String spreadingFactor;
	private String frequency;
	private String netId;
	
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
	@Override
	public String toString() {
		return "TamperEventsDto [slNo=" + slNo + ", commandId=" + commandId + ", reservedByte=" + reservedByte
				+ ", networkType=" + networkType + ", deviceType=" + deviceType + ", meterModel=" + meterModel
				+ ", firmwareVersion=" + firmwareVersion + ", meterConnectionType=" + meterConnectionType
				+ ", valveStatus=" + valveStatus + ", deviceRtc=" + deviceRtc + ", pulseCount=" + pulseCount
				+ ", batteryVoltage=" + batteryVoltage + ", nextUpdateTime=" + nextUpdateTime + ", tamperType="
				+ tamperType + ", lastUpdateTime=" + lastUpdateTime + ", deviceId=" + deviceId + ", applicationId="
				+ applicationId + ", devEui=" + devEui + ", joinEui=" + joinEui + ", deviceAddress=" + deviceAddress
				+ ", receivedAt=" + receivedAt + ", fPort=" + fPort + ", fCnt=" + fCnt + ", payload=" + payload
				+ ", port=" + port + ", gatewayId=" + gatewayId + ", gatewayEui=" + gatewayEui + ", rssi=" + rssi
				+ ", channelRssi=" + channelRssi + ", snr=" + snr + ", bandwidth=" + bandwidth + ", spreadingFactor="
				+ spreadingFactor + ", frequency=" + frequency + ", netId=" + netId + "]";
	}
	
}
