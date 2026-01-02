package com.loraDuoMeter.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "building_details")
public class BuildingEntity {
	public BuildingEntity() {
		super();
	}

	public BuildingEntity(Long id, String buildingId, String name, Integer floors, Integer flats, String area,
			String floorDimension, String address, String state, String country, String latitude, String longitude,
			String dateTime) {
		super();
		this.id = id;
		this.buildingId = buildingId;
		this.name = name;
		this.floors = floors;
		this.flats = flats;
		this.area = area;
		this.floorDimension = floorDimension;
		this.address = address;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dateTime = dateTime;
	
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Long id;

    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "building_name")
    private String name;

    @Column(name = "no_of_floors")
    private Integer floors;

    @Column(name = "no_of_flats")
    private Integer flats;

    @Column(name = "building_dimension")
    private String area;
    
    // NEW FIELD
    @Column(name = "floor_dimension")
    private String floorDimension;

    @Column(name = "address")
    private String address;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    // CHANGED: Using String to match DB 'text' column and allow manual insertion from Frontend
    @Column(name = "date_time")
    private String dateTime;

    // Optional: You can keep this or remove it if you don't want to use it


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFloors() {
		return floors;
	}

	public void setFloors(Integer floors) {
		this.floors = floors;
	}

	public Integer getFlats() {
		return flats;
	}

	public void setFlats(Integer flats) {
		this.flats = flats;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloorDimension() {
		return floorDimension;
	}

	public void setFloorDimension(String floorDimension) {
		this.floorDimension = floorDimension;
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
}
