package com.loraDuoMeter.DTO;

public class DailyUpdates_Dto {

	private long slNno;
	private String valveStatus;
	private String deviceRtc;
	private String pulseCount;
	private String batteryVoltage;
	
//	private String commandId;
//	private String reservedByte;
//	private String networkType;
//	private String deviceType;
//	private String meterModel;
//	private String firmwareVersion;
//	private String meterConnection;
//	private String nextUpdateTime;
//	private String pendingEvent;
//	private String rechargeStatus;
//	private String lastUpdateTime;
//	private String deviceId;
//	private String applicationId;
//	private String devEui;
//	private String joinEui;
//	private String deviceAddress;
//	private String receivedAt;
//	private int fPort;
//	private int fCnt;
//	private String payload;
//	private String port;
//	private String gatewayId;
//	private String gatewayEui;
//	private String rssi;
//	private String channelRssi;
//	private String snr;
//	private String bandwidth;
//	private String spreadingFactor;
//	private String frequency;
//	private String netId;

	public String getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public long getSlNno() {
		return slNno;
	}

	public void setSlNno(long slNno) {
		this.slNno = slNno;
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

	@Override
	public String toString() {
		return "DailyUpdates_Dto [slNno=" + slNno + ", valveStatus=" + valveStatus + ", deviceRtc=" + deviceRtc
				+ ", pulseCount=" + pulseCount + ", batteryVoltage=" + batteryVoltage + "]";
	}
	
	/*public long getSlNno() {
		return slNno;
	}
	public void setSlNno(long slNno) {
		this.slNno = slNno;
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
	@Override
	public String toString() {
		return "DailyUpdates_Dto [slNno=" + slNno + ", commandId=" + commandId + ", reservedByte=" + reservedByte
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
				+ "]";
	}*/
	
	
	
}
