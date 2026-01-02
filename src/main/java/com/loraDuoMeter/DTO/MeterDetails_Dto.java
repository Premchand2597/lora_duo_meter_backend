package com.loraDuoMeter.DTO;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class MeterDetails_Dto {

	private long slNo;
	private String dateTime;
	private String buildingName;
	private String floorNo;
	private String flatNo;
	private String residentType;
	private String residentName;
	private String meterSlNo;
	private String networkType;
	private String deviceType;
	private String valveStatus;
	private String batteryVoltage;
	private String pulseCount;
	private String gatewayId;
	private String meterConnectionType;
	private String meterModel;
	
//	private String meterMacAddress;
//	private String firmwareVersion;
//	private String deviceRtc;
//	private String rechargeAmount;
//	private String rechargeStatus;
//	private String manufacturerName;
//	private String manufactureYear;
//	private String lastInsertedTime;
//	private String buildingId;
	
	private List<DailyUpdates_Dto> dailyUpdatesData;
	
	
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
	public List<DailyUpdates_Dto> getDailyUpdatesData() {
		return dailyUpdatesData;
	}
	public void setDailyUpdatesData(List<DailyUpdates_Dto> dailyUpdatesData) {
		this.dailyUpdatesData = dailyUpdatesData;
	}
	public String getPulseCount() {
		return pulseCount;
	}
	public void setPulseCount(String pulseCount) {
		this.pulseCount = pulseCount;
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
		return "MeterDetails_Dto [slNo=" + slNo + ", dateTime=" + dateTime + ", buildingName=" + buildingName
				+ ", floorNo=" + floorNo + ", flatNo=" + flatNo + ", residentType=" + residentType + ", residentName="
				+ residentName + ", meterSlNo=" + meterSlNo + ", networkType=" + networkType + ", deviceType="
				+ deviceType + ", valveStatus=" + valveStatus + ", batteryVoltage=" + batteryVoltage + ", pulseCount="
				+ pulseCount + ", gatewayId=" + gatewayId + ", meterConnectionType=" + meterConnectionType
				+ ", meterModel=" + meterModel + ", dailyUpdatesData=" + dailyUpdatesData + "]";
	}
	
	/*public long getSlNo() {
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
	public String getMeterMacAddress() {
		return meterMacAddress;
	}
	public void setMeterMacAddress(String meterMacAddress) {
		this.meterMacAddress = meterMacAddress;
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
	public String getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String getRechargeStatus() {
		return rechargeStatus;
	}
	public void setRechargeStatus(String rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getManufactureYear() {
		return manufactureYear;
	}
	public void setManufactureYear(String manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getLastInsertedTime() {
		return lastInsertedTime;
	}
	public void setLastInsertedTime(String lastInsertedTime) {
		this.lastInsertedTime = lastInsertedTime;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public List<DailyUpdates_Dto> getDailyUpdatesData() {
		return dailyUpdatesData;
	}
	public void setDailyUpdatesData(List<DailyUpdates_Dto> dailyUpdatesData) {
		this.dailyUpdatesData = dailyUpdatesData;
	}
	@Override
	public String toString() {
		return "MeterDetails_Dto [slNo=" + slNo + ", dateTime=" + dateTime + ", buildingName=" + buildingName
				+ ", floorNo=" + floorNo + ", flatNo=" + flatNo + ", residentType=" + residentType + ", residentName="
				+ residentName + ", meterSlNo=" + meterSlNo + ", meterMacAddress=" + meterMacAddress + ", networkType="
				+ networkType + ", deviceType=" + deviceType + ", meterModel=" + meterModel + ", firmwareVersion="
				+ firmwareVersion + ", meterConnectionType=" + meterConnectionType + ", valveStatus=" + valveStatus
				+ ", batteryVoltage=" + batteryVoltage + ", deviceRtc=" + deviceRtc + ", pulseCount=" + pulseCount
				+ ", rechargeAmount=" + rechargeAmount + ", rechargeStatus=" + rechargeStatus + ", manufacturerName="
				+ manufacturerName + ", manufactureYear=" + manufactureYear + ", gatewayId=" + gatewayId
				+ ", lastInsertedTime=" + lastInsertedTime + ", buildingId=" + buildingId + ", dailyUpdatesData="
				+ dailyUpdatesData + "]";
	}*/
}
