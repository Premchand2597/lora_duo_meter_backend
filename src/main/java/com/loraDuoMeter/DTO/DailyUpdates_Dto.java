package com.loraDuoMeter.DTO;

public class DailyUpdates_Dto {

	private long slNno;
	private String valveStatus;
	private String deviceRtc;
	private String pulseCount;
	private String batteryVoltage;	

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
	
	
}
