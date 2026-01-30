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
	

}
