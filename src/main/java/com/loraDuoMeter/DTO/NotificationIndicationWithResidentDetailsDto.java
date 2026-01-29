package com.loraDuoMeter.DTO;

public class NotificationIndicationWithResidentDetailsDto {
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
	
	private String resident_name;
	private String email;
	private String primary_mobile;
	private String secondary_mobile;
	private String building_name;
	private String resident_type;
	private String resident_address;
	
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
	public String getResident_name() {
		return resident_name;
	}
	public void setResident_name(String resident_name) {
		this.resident_name = resident_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrimary_mobile() {
		return primary_mobile;
	}
	public void setPrimary_mobile(String primary_mobile) {
		this.primary_mobile = primary_mobile;
	}
	public String getSecondary_mobile() {
		return secondary_mobile;
	}
	public void setSecondary_mobile(String secondary_mobile) {
		this.secondary_mobile = secondary_mobile;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getResident_type() {
		return resident_type;
	}
	public void setResident_type(String resident_type) {
		this.resident_type = resident_type;
	}
	public String getResident_address() {
		return resident_address;
	}
	public void setResident_address(String resident_address) {
		this.resident_address = resident_address;
	}
	@Override
	public String toString() {
		return "NotificationIndicationWithResidentDetailsDto [slNo=" + slNo + ", deviceId=" + deviceId
				+ ", applicationId=" + applicationId + ", devEui=" + devEui + ", powerUpNotification="
				+ powerUpNotification + ", dailyUpdateNotification=" + dailyUpdateNotification
				+ ", rechargeFinishNotification=" + rechargeFinishNotification + ", batteryCutOffNotification="
				+ batteryCutOffNotification + ", tamperEventNotification=" + tamperEventNotification
				+ ", gasLeakNotification=" + gasLeakNotification + ", manualClickDeviceEventNotification="
				+ manualClickDeviceEventNotification + ", meterInfoNotification=" + meterInfoNotification
				+ ", instantaneousDataNotification=" + instantaneousDataNotification + ", resident_name="
				+ resident_name + ", email=" + email + ", primary_mobile=" + primary_mobile + ", secondary_mobile="
				+ secondary_mobile + ", building_name=" + building_name + ", resident_type=" + resident_type
				+ ", resident_address=" + resident_address + "]";
	}
}
