package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resident_details")
public class ResidentEntity {

	public ResidentEntity() {
		super();
	}

	public ResidentEntity(Long id, String residentId, String buildingName, String buildingId, String floorNo,
			String flatNo, String fullName, String type, String mobile, String altPhone, String email, String address,
			String state, String country, String dateTime,  String aadhaarProof,
			String houseDocuments) {
		super();
		this.id = id;
		this.residentId = residentId;
		this.buildingName = buildingName;
		this.buildingId = buildingId;
		this.floorNo = floorNo;
		this.flatNo = flatNo;
		this.fullName = fullName;
		this.type = type;
		this.mobile = mobile;
		this.altPhone = altPhone;
		this.email = email;
		this.address = address;
		this.state = state;
		this.country = country;
		this.dateTime = dateTime;
	
		this.aadhaarProof = aadhaarProof;
		this.houseDocuments = houseDocuments;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Long id;

    @Column(name = "resident_id")
    private String residentId;

    @Column(name = "building_name")
    private String buildingName;
    
    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "floor_no")
    private String floorNo;

    @Column(name = "flat_no")
    private String flatNo;

    @Column(name = "resident_name")
    private String fullName;

    @Column(name = "resident_type")
    private String type; // Owner/Tenant

    @Column(name = "primary_mobile")
    private String mobile;

    @Column(name = "secondary_mobile")
    private String altPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "resident_address")
    private String address;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "date_time")
    private String dateTime;

    
    // Placeholders for file paths (since actual file upload requires Multipart handling)
    @Column(name = "aadhaar_proof")
    private String aadhaarProof;
    
    @Column(name = "house_documents")
    private String houseDocuments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getAadhaarProof() {
		return aadhaarProof;
	}

	public void setAadhaarProof(String aadhaarProof) {
		this.aadhaarProof = aadhaarProof;
	}

	public String getHouseDocuments() {
		return houseDocuments;
	}

	public void setHouseDocuments(String houseDocuments) {
		this.houseDocuments = houseDocuments;
	}
	
	
}
