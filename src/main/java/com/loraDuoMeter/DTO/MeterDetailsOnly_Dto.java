package com.loraDuoMeter.DTO;

public class MeterDetailsOnly_Dto {

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
		return "MeterDetailsOnly_Dto [slNo=" + slNo + ", dateTime=" + dateTime + ", buildingName=" + buildingName
				+ ", floorNo=" + floorNo + ", flatNo=" + flatNo + ", residentType=" + residentType + ", residentName="
				+ residentName + ", meterSlNo=" + meterSlNo + ", networkType=" + networkType + ", deviceType="
				+ deviceType + ", valveStatus=" + valveStatus + ", batteryVoltage=" + batteryVoltage + ", pulseCount="
				+ pulseCount + ", gatewayId=" + gatewayId + ", meterConnectionType=" + meterConnectionType
				+ ", meterModel=" + meterModel + "]";
	}
}
