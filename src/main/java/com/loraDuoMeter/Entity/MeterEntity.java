package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "meter_details")
public class MeterEntity {
	public MeterEntity(Long id, String dateTime, String buildingName, String buildingId, String floorNo, String flatNo,
			String residentType, String residentName, String serialNo, String macAddress, String networkType,
			String deviceType, String model, String firmware, String connectionType, String valveStatus,
			String batteryVoltage, String deviceRtc, String pulseCount, String rechargeAmount, String rechargeStatus,
			String manufacturer, String makeYear, String gatewayId, String residentId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.buildingName = buildingName;
		this.buildingId = buildingId;
		this.floorNo = floorNo;
		this.flatNo = flatNo;
		this.residentType = residentType;
		this.residentName = residentName;
		this.serialNo = serialNo;
		this.macAddress = macAddress;
		this.networkType = networkType;
		this.deviceType = deviceType;
		this.model = model;
		this.firmware = firmware;
		this.connectionType = connectionType;
		this.valveStatus = valveStatus;
		this.batteryVoltage = batteryVoltage;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.rechargeAmount = rechargeAmount;
		this.rechargeStatus = rechargeStatus;
		this.manufacturer = manufacturer;
		this.makeYear = makeYear;
		this.gatewayId = gatewayId;
		this.residentId = residentId;
	}

	public MeterEntity() {
		super();
	}

	public MeterEntity(Long id, String dateTime, String buildingName, String buildingId, String floorNo, String flatNo,
			String residentType, String residentName, String serialNo, String macAddress, String networkType,
			String deviceType, String model, String firmware, String connectionType, String valveStatus,
			String batteryVoltage, String deviceRtc, String pulseCount, String rechargeAmount, String rechargeStatus,
			String manufacturer, String makeYear, String gatewayId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.buildingName = buildingName;
		this.buildingId = buildingId;
		this.floorNo = floorNo;
		this.flatNo = flatNo;
		this.residentType = residentType;
		this.residentName = residentName;
		this.serialNo = serialNo;
		this.macAddress = macAddress;
		this.networkType = networkType;
		this.deviceType = deviceType;
		this.model = model;
		this.firmware = firmware;
		this.connectionType = connectionType;
		this.valveStatus = valveStatus;
		this.batteryVoltage = batteryVoltage;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.rechargeAmount = rechargeAmount;
		this.rechargeStatus = rechargeStatus;
		this.manufacturer = manufacturer;
		this.makeYear = makeYear;
		this.gatewayId = gatewayId;
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Long id;

    @Column(name = "date_time")
    private String dateTime;

    @Column(name = "building_name")
    private String buildingName;
    
    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "floor_no")
    private String floorNo;

    @Column(name = "flat_no")
    private String flatNo;

    @Column(name = "resident_type")
    private String residentType;

    @Column(name = "resident_name")
    private String residentName;

    @Column(name = "meter_sl_no")
    private String serialNo;

    @Column(name = "meter_mac_address")
    private String macAddress;

    @Column(name = "network_type")
    private String networkType;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "meter_model")
    private String model;

    @Column(name = "firmware_version")
    private String firmware;

    @Column(name = "meter_connection_type")
    private String connectionType;

    @Column(name = "valve_status")
    private String valveStatus;

    @Column(name = "battery_voltage")
    private String batteryVoltage;

    @Column(name = "device_rtc")
    private String deviceRtc;

    @Column(name = "pulse_count")
    private String pulseCount;

    @Column(name = "recharge_amount")
    private String rechargeAmount;

    @Column(name = "recharge_status")
    private String rechargeStatus;

    @Column(name = "manufacturer_name")
    private String manufacturer;

    @Column(name = "manufacture_year")
    private String makeYear;

    @Column(name = "gateway_id")
    private String gatewayId;

 // --- NEW FIELD: RESIDENT ID ---
    @Column(name = "resident_id") 
    private String residentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	

}
