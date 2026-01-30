package com.loraDuoMeter.DTO;

public class ManualDeviceNotificationDisplay_DTO {
	
	public Long slNo;
    public String networkType;
    public String deviceType;
    public String meterConnectionType;
    public String meterModel;
    public String valveStatus;
    public String deviceRtc;
    public String pulseCount;
    public String batteryVoltage;
    public String nextUpdateTime;
    public String pendingEvent;
    public String rechargeStatus;
    public String lastUpdateTime;
    public String devEui;
    public String gatewayEui;
    public Integer fPort;
    public String receivedAt;
    public String buildingName;
    public String residentName;
	public Long getSlNo() {
		return slNo;
	}
	public void setSlNo(Long slNo) {
		this.slNo = slNo;
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
	public String getMeterConnectionType() {
		return meterConnectionType;
	}
	public void setMeterConnectionType(String meterConnectionType) {
		this.meterConnectionType = meterConnectionType;
	}
	public String getMeterModel() {
		return meterModel;
	}
	public void setMeterModel(String meterModel) {
		this.meterModel = meterModel;
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
	public String getDevEui() {
		return devEui;
	}
	public void setDevEui(String devEui) {
		this.devEui = devEui;
	}
	public String getGatewayEui() {
		return gatewayEui;
	}
	public void setGatewayEui(String gatewayEui) {
		this.gatewayEui = gatewayEui;
	}
	public Integer getfPort() {
		return fPort;
	}
	public void setfPort(Integer fPort) {
		this.fPort = fPort;
	}
	public String getReceivedAt() {
		return receivedAt;
	}
	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
	public ManualDeviceNotificationDisplay_DTO(Long slNo, String networkType, String deviceType, String meterConnectionType,
			String meterModel, String valveStatus, String deviceRtc, String pulseCount, String batteryVoltage,
			String nextUpdateTime, String pendingEvent, String rechargeStatus, String lastUpdateTime, String devEui,
			String gatewayEui, Integer fPort, String receivedAt, String buildingName, String residentName) {
		super();
		this.slNo = slNo;
		this.networkType = networkType;
		this.deviceType = deviceType;
		this.meterConnectionType = meterConnectionType;
		this.meterModel = meterModel;
		this.valveStatus = valveStatus;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.batteryVoltage = batteryVoltage;
		this.nextUpdateTime = nextUpdateTime;
		this.pendingEvent = pendingEvent;
		this.rechargeStatus = rechargeStatus;
		this.lastUpdateTime = lastUpdateTime;
		this.devEui = devEui;
		this.gatewayEui = gatewayEui;
		this.fPort = fPort;
		this.receivedAt = receivedAt;
		this.buildingName = buildingName;
		this.residentName = residentName;
	}
	public ManualDeviceNotificationDisplay_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
}
