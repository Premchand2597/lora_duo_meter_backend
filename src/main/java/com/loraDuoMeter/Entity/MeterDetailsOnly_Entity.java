package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MeterDetailsOnly_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sl_no")
	private long slNo;
	@Column(name="date_time")
	private String dateTime;
	@Column(name="building_name")
	private String buildingName;
	@Column(name="floor_no")
	private String floorNo;
	@Column(name="flat_no")
	private String flatNo;
	@Column(name="resident_type")
	private String residentType;
	@Column(name="resident_name")
	private String residentName;
	@Column(name="meter_sl_no")
	private String meterSlNo;
	@Column(name="device_type")
	private String deviceType;
	@Column(name="valve_status")
	private String valveStatus;
	@Column(name="battery_voltage")
	private String batteryVoltage;
	@Column(name="pulse_count")
	private String pulseCount;
	@Column(name="network_type")
	private String networkType;
	@Column(name="gateway_id")
	private String gatewayId;
	@Column(name="meter_connection_type")
	private String meterConnectionType;
	@Column(name="meter_model")
	private String meterModel;
	
	
	/*@Column(name="meter_mac_address")
	private String meterMacAddress;
	@Column(name="firmware_version")
	private String firmwareVersion;
	@Column(name="device_rtc")
	private String deviceRtc;
	@Column(name="recharge_amount")
	private String rechargeAmount;
	@Column(name="recharge_status")
	private String rechargeStatus;
	@Column(name="manufacturer_name")
	private String manufacturerName;
	@Column(name="manufacture_year")
	private String manufactureYear;
	@Column(name="last_inserted_time")
	private String lastInsertedTime;
	@Column(name="building_id")
	private String buildingId;*/

	public long getSlNo() {
		return slNo;
	}
	public void setSlNo(long slNo) {
		this.slNo = slNo;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
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
	public String getMeterSlNo() {
		return meterSlNo;
	}
	public void setMeterSlNo(String meterSlNo) {
		this.meterSlNo = meterSlNo;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getValveStatus() {
		return valveStatus;
	}
	public void setValveStatus(String valveStatus) {
		this.valveStatus = valveStatus;
	}
	public String getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public String getPulseCount() {
		return pulseCount;
	}
	public void setPulseCount(String pulseCount) {
		this.pulseCount = pulseCount;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
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
	@Override
	public String toString() {
		return "MeterDetailsOnly_Entity [slNo=" + slNo + ", dateTime=" + dateTime + ", buildingName=" + buildingName
				+ ", floorNo=" + floorNo + ", flatNo=" + flatNo + ", residentType=" + residentType + ", residentName="
				+ residentName + ", meterSlNo=" + meterSlNo + ", deviceType=" + deviceType + ", valveStatus="
				+ valveStatus + ", batteryVoltage=" + batteryVoltage + ", pulseCount=" + pulseCount + ", networkType="
				+ networkType + ", gatewayId=" + gatewayId + ", meterConnectionType=" + meterConnectionType
				+ ", meterModel=" + meterModel + "]";
	}
}
