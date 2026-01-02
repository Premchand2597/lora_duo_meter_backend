package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gateway_details")
public class GatewayEntity {

	
	public GatewayEntity() {
		super();
	}

	public GatewayEntity(Long id, String dateTime, String buildingName, String buildingId, String floorNo,
			String gatewayId, String internetType, String status, String maxMeters, String model, String manufacturer,
			String makeYear, String latitude, String longitude, String firmware, String rssiId, String channelRssi,
			String channelIndex, String bandwidth, String frequency, String netId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.buildingName = buildingName;
		this.buildingId = buildingId;
		this.floorNo = floorNo;
		this.gatewayId = gatewayId;
		this.internetType = internetType;
		this.status = status;
		this.maxMeters = maxMeters;
		this.model = model;
		this.manufacturer = manufacturer;
		this.makeYear = makeYear;
		this.latitude = latitude;
		this.longitude = longitude;
		this.firmware = firmware;
		this.rssiId = rssiId;
		this.channelRssi = channelRssi;
		this.channelIndex = channelIndex;
		this.bandwidth = bandwidth;
		this.frequency = frequency;
		this.netId = netId;
		
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

    @Column(name = "gateway_id")
    private String gatewayId;

    @Column(name = "internet_connection_type")
    private String internetType;

    @Column(name = "connection_status")
    private String status;

    @Column(name = "max_connection_count_per_gw")
    private String maxMeters;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer_name")
    private String manufacturer;

    @Column(name = "manufacture_year")
    private String makeYear;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "gw_firmware_version")
    private String firmware;

    @Column(name = "rssi")
    private String rssiId;

    @Column(name = "channel_rssi")
    private String channelRssi;

    @Column(name = "channel_index")
    private String channelIndex;

    @Column(name = "bandwidth")
    private String bandwidth;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "net_id")
    private String netId;



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

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getInternetType() {
		return internetType;
	}

	public void setInternetType(String internetType) {
		this.internetType = internetType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaxMeters() {
		return maxMeters;
	}

	public void setMaxMeters(String maxMeters) {
		this.maxMeters = maxMeters;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public String getRssiId() {
		return rssiId;
	}

	public void setRssiId(String rssiId) {
		this.rssiId = rssiId;
	}

	public String getChannelRssi() {
		return channelRssi;
	}

	public void setChannelRssi(String channelRssi) {
		this.channelRssi = channelRssi;
	}

	public String getChannelIndex() {
		return channelIndex;
	}

	public void setChannelIndex(String channelIndex) {
		this.channelIndex = channelIndex;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	
}
