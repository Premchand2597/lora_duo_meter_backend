package com.loraDuoMeter.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "tariffs")
public class TariffEntity {

	public TariffEntity() {
		super();
	}
	public TariffEntity(Long id, String location, String buildingIds, Double unitPrice, Double securityDeposit,
			Double connectionFee, Double processingFee, Double disconnectFee, Double lateCharge,
			Double initialRechargeFee, Double replacementFee) {
		super();
		this.id = id;
		this.location = location;
		this.buildingIds = buildingIds;
		this.unitPrice = unitPrice;
		this.securityDeposit = securityDeposit;
		this.connectionFee = connectionFee;
		this.processingFee = processingFee;
		this.disconnectFee = disconnectFee;
		this.lateCharge = lateCharge;
		this.initialRechargeFee = initialRechargeFee;
		this.replacementFee = replacementFee;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    // Storing building IDs as a comma-separated string (e.g., "Buil1,Buil2")
    // Use @Lob if the list is expected to be very long, otherwise standard String is fine (255 chars).
    // Let's assume it can be long.
    @Lob 
    private String buildingIds; 

    private Double unitPrice;
    private Double securityDeposit;
    private Double connectionFee;
    private Double processingFee;
    private Double disconnectFee;
    private Double lateCharge;
    private Double initialRechargeFee;
    private Double replacementFee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBuildingIds() {
		return buildingIds;
	}
	public void setBuildingIds(String buildingIds) {
		this.buildingIds = buildingIds;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public Double getConnectionFee() {
		return connectionFee;
	}
	public void setConnectionFee(Double connectionFee) {
		this.connectionFee = connectionFee;
	}
	public Double getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(Double processingFee) {
		this.processingFee = processingFee;
	}
	public Double getDisconnectFee() {
		return disconnectFee;
	}
	public void setDisconnectFee(Double disconnectFee) {
		this.disconnectFee = disconnectFee;
	}
	public Double getLateCharge() {
		return lateCharge;
	}
	public void setLateCharge(Double lateCharge) {
		this.lateCharge = lateCharge;
	}
	public Double getInitialRechargeFee() {
		return initialRechargeFee;
	}
	public void setInitialRechargeFee(Double initialRechargeFee) {
		this.initialRechargeFee = initialRechargeFee;
	}
	public Double getReplacementFee() {
		return replacementFee;
	}
	public void setReplacementFee(Double replacementFee) {
		this.replacementFee = replacementFee;
	}
	
}
