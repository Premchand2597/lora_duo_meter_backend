// src/main/java/com/loraDuoMeter/Entity/ZoneEntity.java
package com.loraDuoMeter.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "zones")
public class ZoneEntity {
    public ZoneEntity() {
		super();
	}

	public ZoneEntity(Long id, String country, String state, String district, String zoneName) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.district = district;
		this.zoneName = zoneName;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String zoneName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
}