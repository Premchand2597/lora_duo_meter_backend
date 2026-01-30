package com.loraDuoMeter.DTO;

public class MQTT_Listner_Receive_Data_Dto {
	
	 // Device
    private String deviceId;
    private String applicationId;
    private String devEui;
    private String joinEui;
    private String devAddr;

    // Uplink
    private int fPort;
    private int fCnt;
    private String frmPayload;
    private String receivedAt;

    // Gateway
    private String gatewayId;
    private String gatewayEui;
    private int rssi;
    private int channelRssi;
    private double snr;
    private int channelIndex;

    // Radio
    private int bandwidth;
    private int spreadingFactor;
    private String frequency;
    private String netId;

    // Decoded
    private String hexPayload;

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

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
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

	public String getFrmPayload() {
		return frmPayload;
	}

	public void setFrmPayload(String frmPayload) {
		this.frmPayload = frmPayload;
	}

	public String getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
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

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getChannelRssi() {
		return channelRssi;
	}

	public void setChannelRssi(int channelRssi) {
		this.channelRssi = channelRssi;
	}

	public double getSnr() {
		return snr;
	}

	public void setSnr(double snr) {
		this.snr = snr;
	}

	public int getChannelIndex() {
		return channelIndex;
	}

	public void setChannelIndex(int channelIndex) {
		this.channelIndex = channelIndex;
	}

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getSpreadingFactor() {
		return spreadingFactor;
	}

	public void setSpreadingFactor(int spreadingFactor) {
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

	public String getHexPayload() {
		return hexPayload;
	}

	public void setHexPayload(String hexPayload) {
		this.hexPayload = hexPayload;
	}
    
    

}
