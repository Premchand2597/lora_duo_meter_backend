package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "water_tariffs")
public class WaterTariffEntity {
	public WaterTariffEntity(Long id, String location, String buildingIds, Double unitPrice, Double securityDeposit,
			Double replacementFee, Double meterCharges, Double sanitaryCharges, Double borewellCharges,
			Double otherCharges, Double initialRechargeFee, Double connectionFee, Double processingFee,
			Double disconnectFee, Double lateCharge, Long zoneId, String type, String lastUpdated) {
		super();
		this.id = id;
		this.location = location;
		this.buildingIds = buildingIds;
		this.unitPrice = unitPrice;
		this.securityDeposit = securityDeposit;
		this.replacementFee = replacementFee;
		this.meterCharges = meterCharges;
		this.sanitaryCharges = sanitaryCharges;
		this.borewellCharges = borewellCharges;
		this.otherCharges = otherCharges;
		this.initialRechargeFee = initialRechargeFee;
		this.connectionFee = connectionFee;
		this.processingFee = processingFee;
		this.disconnectFee = disconnectFee;
		this.lateCharge = lateCharge;
		this.zoneId = zoneId;
		this.type = type;
		this.lastUpdated = lastUpdated;
	}

	public WaterTariffEntity(Long id, String location, String buildingIds, Double unitPrice, Double securityDeposit,
			Double replacementFee, Double meterCharges, Double sanitaryCharges, Double borewellCharges,
			Double otherCharges, Double initialRechargeFee, Double connectionFee, Double processingFee,
			Double disconnectFee, Double lateCharge, Long zoneId, String type) {
		super();
		this.id = id;
		this.location = location;
		this.buildingIds = buildingIds;
		this.unitPrice = unitPrice;
		this.securityDeposit = securityDeposit;
		this.replacementFee = replacementFee;
		this.meterCharges = meterCharges;
		this.sanitaryCharges = sanitaryCharges;
		this.borewellCharges = borewellCharges;
		this.otherCharges = otherCharges;
		this.initialRechargeFee = initialRechargeFee;
		this.connectionFee = connectionFee;
		this.processingFee = processingFee;
		this.disconnectFee = disconnectFee;
		this.lateCharge = lateCharge;
		this.zoneId = zoneId;
		this.type = type;
	}

	public WaterTariffEntity() {
		super();
	}

	public WaterTariffEntity(Long id, String location, String buildingIds, Double unitPrice, Double securityDeposit,
			Double replacementFee, Double meterCharges, Double sanitaryCharges, Double borewellCharges,
			Double otherCharges, Double initialRechargeFee, Double connectionFee, Double processingFee,
			Double disconnectFee, Double lateCharge, Long zoneId) {
		super();
		this.id = id;
		this.location = location;
		this.buildingIds = buildingIds;
		this.unitPrice = unitPrice;
		this.securityDeposit = securityDeposit;
		this.replacementFee = replacementFee;
		this.meterCharges = meterCharges;
		this.sanitaryCharges = sanitaryCharges;
		this.borewellCharges = borewellCharges;
		this.otherCharges = otherCharges;
		this.initialRechargeFee = initialRechargeFee;
		this.connectionFee = connectionFee;
		this.processingFee = processingFee;
		this.disconnectFee = disconnectFee;
		this.lateCharge = lateCharge;
		this.zoneId = zoneId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @Lob 
    private String buildingIds; 

    // Primary Rates
    private Double unitPrice;
    private Double securityDeposit;
    private Double replacementFee;

    // Water Specific Charges (From Row 3 of index.jsx)
    private Double meterCharges;
    private Double sanitaryCharges;
    private Double borewellCharges;
    private Double otherCharges;
    private Double initialRechargeFee;

    // Operational Fees (From Row 4 of index.jsx)
    private Double connectionFee;
    private Double processingFee;
    private Double disconnectFee;
    private Double lateCharge;
    
    private Long zoneId; // To store the selected Zone ID
    private String type;
    
    @Column(name = "last_updated")
    private String lastUpdated; // Must be a normal String, not LocalDateTime

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

	public Double getReplacementFee() {
		return replacementFee;
	}

	public void setReplacementFee(Double replacementFee) {
		this.replacementFee = replacementFee;
	}

	public Double getMeterCharges() {
		return meterCharges;
	}

	public void setMeterCharges(Double meterCharges) {
		this.meterCharges = meterCharges;
	}

	public Double getSanitaryCharges() {
		return sanitaryCharges;
	}

	public void setSanitaryCharges(Double sanitaryCharges) {
		this.sanitaryCharges = sanitaryCharges;
	}

	public Double getBorewellCharges() {
		return borewellCharges;
	}

	public void setBorewellCharges(Double borewellCharges) {
		this.borewellCharges = borewellCharges;
	}

	public Double getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(Double otherCharges) {
		this.otherCharges = otherCharges;
	}

	public Double getInitialRechargeFee() {
		return initialRechargeFee;
	}

	public void setInitialRechargeFee(Double initialRechargeFee) {
		this.initialRechargeFee = initialRechargeFee;
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

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
