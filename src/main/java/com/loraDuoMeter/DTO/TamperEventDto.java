package com.loraDuoMeter.DTO;

public class TamperEventDto {
    public TamperEventDto() {
		super();
	}
	public TamperEventDto(Integer slNo, String deviceType, String devEui, String tamperType, String eventTime,
			String eventStatus, String lastInsertedDate, String buildingName, String buildingId, String residentName,
			String floorNo, String flatNo, String residentType, String address, String state, String country) {
		super();
		this.slNo = slNo;
		this.deviceType = deviceType;
		this.devEui = devEui;
		this.tamperType = tamperType;
		this.eventTime = eventTime;
		this.eventStatus = eventStatus;
		this.lastInsertedDate = lastInsertedDate;
		this.buildingName = buildingName;
		this.buildingId = buildingId;
		this.residentName = residentName;
		this.floorNo = floorNo;
		this.flatNo = flatNo;
		this.residentType = residentType;
		this.address = address;
		this.state = state;
		this.country = country;
	}
	// Original Tamper Fields
    private Integer slNo;
    private String deviceType;
    private String devEui;
    private String tamperType;
    private String eventTime;
    private String eventStatus;
    private String lastInsertedDate;

    // Building & Resident Details
    private String buildingName;
    private String buildingId;
    
    // NEW FIELDS
    private String residentName;
    private String floorNo;
    private String flatNo;
    private String residentType; // e.g. Owner/Tenant
    
    private String address;
    private String state;
    private String country;
	public Integer getSlNo() {
		return slNo;
	}
	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDevEui() {
		return devEui;
	}
	public void setDevEui(String devEui) {
		this.devEui = devEui;
	}
	public String getTamperType() {
		return tamperType;
	}
	public void setTamperType(String tamperType) {
		this.tamperType = tamperType;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getLastInsertedDate() {
		return lastInsertedDate;
	}
	public void setLastInsertedDate(String lastInsertedDate) {
		this.lastInsertedDate = lastInsertedDate;
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
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}