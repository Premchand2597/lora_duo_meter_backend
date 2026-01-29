package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification_indication_table")
public class NotificationIndicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private long slNo;
	@Column(name = "device_id")
	private String deviceId;
	@Column(name = "application_id")
	private String applicationId;
	@Column(name = "dev_eui")
	private String devEui;
	@Column(name = "power_up_notification")
	private boolean powerUpNotification;
	@Column(name = "daily_update_notification")
	private boolean dailyUpdateNotification;
	@Column(name = "recharge_finish_notification")
	private boolean rechargeFinishNotification; 
	@Column(name = "battery_cut_off_notification")
	private boolean batteryCutOffNotification;
	@Column(name = "tamper_event_notification")
	private boolean tamperEventNotification;
	@Column(name = "gas_leak_notification")
	private boolean gasLeakNotification;
	@Column(name = "manual_click_device_event_notification")
	private boolean manualClickDeviceEventNotification;
	@Column(name = "meter_info_notification")
	private boolean meterInfoNotification; 
	@Column(name = "instantaneous_data_notification")
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
		return "NotificationIndicationEntity [slNo=" + slNo + ", deviceId=" + deviceId + ", applicationId="
				+ applicationId + ", devEui=" + devEui + ", powerUpNotification=" + powerUpNotification
				+ ", dailyUpdateNotification=" + dailyUpdateNotification + ", rechargeFinishNotification="
				+ rechargeFinishNotification + ", batteryCutOffNotification=" + batteryCutOffNotification
				+ ", tamperEventNotification=" + tamperEventNotification + ", gasLeakNotification="
				+ gasLeakNotification + ", manualClickDeviceEventNotification=" + manualClickDeviceEventNotification
				+ ", meterInfoNotification=" + meterInfoNotification + ", instantaneousDataNotification="
				+ instantaneousDataNotification + "]";
	}
}
