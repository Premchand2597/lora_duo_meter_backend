package com.loraDuoMeter.DTO;

public class NotificationIndicationDto {
	private long slNo;
	private String deviceId;
	private String applicationId;
	private String devEui;
	private boolean powerUpNotification;
	private boolean dailyUpdateNotification;
	private boolean rechargeFinishNotification; 
	private boolean batteryCutOffNotification;
	private boolean tamperEventNotification;
	private boolean gasLeakNotification;
	private boolean manualClickDeviceEventNotification;
	private boolean meterInfoNotification; 
	private boolean instantaneousDataNotification;
	
	public long getSlNo() {
		return slNo;
	}
	public void setSlNo(long slNo) {
		this.slNo = slNo;
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
	public boolean isPowerUpNotification() {
		return powerUpNotification;
	}
	public void setPowerUpNotification(boolean powerUpNotification) {
		this.powerUpNotification = powerUpNotification;
	}
	public boolean isDailyUpdateNotification() {
		return dailyUpdateNotification;
	}
	public void setDailyUpdateNotification(boolean dailyUpdateNotification) {
		this.dailyUpdateNotification = dailyUpdateNotification;
	}
	public boolean isRechargeFinishNotification() {
		return rechargeFinishNotification;
	}
	public void setRechargeFinishNotification(boolean rechargeFinishNotification) {
		this.rechargeFinishNotification = rechargeFinishNotification;
	}
	public boolean isBatteryCutOffNotification() {
		return batteryCutOffNotification;
	}
	public void setBatteryCutOffNotification(boolean batteryCutOffNotification) {
		this.batteryCutOffNotification = batteryCutOffNotification;
	}
	public boolean isTamperEventNotification() {
		return tamperEventNotification;
	}
	public void setTamperEventNotification(boolean tamperEventNotification) {
		this.tamperEventNotification = tamperEventNotification;
	}
	public boolean isGasLeakNotification() {
		return gasLeakNotification;
	}
	public void setGasLeakNotification(boolean gasLeakNotification) {
		this.gasLeakNotification = gasLeakNotification;
	}
	public boolean isManualClickDeviceEventNotification() {
		return manualClickDeviceEventNotification;
	}
	public void setManualClickDeviceEventNotification(boolean manualClickDeviceEventNotification) {
		this.manualClickDeviceEventNotification = manualClickDeviceEventNotification;
	}
	public boolean isMeterInfoNotification() {
		return meterInfoNotification;
	}
	public void setMeterInfoNotification(boolean meterInfoNotification) {
		this.meterInfoNotification = meterInfoNotification;
	}
	public boolean isInstantaneousDataNotification() {
		return instantaneousDataNotification;
	}
	public void setInstantaneousDataNotification(boolean instantaneousDataNotification) {
		this.instantaneousDataNotification = instantaneousDataNotification;
	}
	@Override
	public String toString() {
		return "NotificationIndicationDto [slNo=" + slNo + ", deviceId=" + deviceId + ", applicationId=" + applicationId
				+ ", devEui=" + devEui + ", powerUpNotification=" + powerUpNotification + ", dailyUpdateNotification="
				+ dailyUpdateNotification + ", rechargeFinishNotification=" + rechargeFinishNotification
				+ ", batteryCutOffNotification=" + batteryCutOffNotification + ", tamperEventNotification="
				+ tamperEventNotification + ", gasLeakNotification=" + gasLeakNotification
				+ ", manualClickDeviceEventNotification=" + manualClickDeviceEventNotification
				+ ", meterInfoNotification=" + meterInfoNotification + ", instantaneousDataNotification="
				+ instantaneousDataNotification + "]";
	}
}
