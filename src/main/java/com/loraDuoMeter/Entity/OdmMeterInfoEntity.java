package com.loraDuoMeter.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "odm_meter_info_table")
@Data
@NoArgsConstructor // Added for JPA
public class OdmMeterInfoEntity {

    public OdmMeterInfoEntity() {
		super();
	}

	public OdmMeterInfoEntity(String slNo, String commandId, String reservedByte, String networkType, String deviceType,
			String meterModel, String firmwareVersion, String meterConnectionType, String valveStatus, String deviceRtc,
			String pulseCount, String batteryVoltage, String pendingEvent,  String lastUpdateTime,
			String gatewayId, String gatewayEui, String devEui, String rechargeStatus, String creditLimit01,
			String creditLimit02, String leakDetector1, String leakDetector2, String perLitrePrice,
			String availableAmount, String batteryVoltageCuttOff) {
		super();
		this.slNo = slNo;
		this.commandId = commandId;
		this.reservedByte = reservedByte;
		this.networkType = networkType;
		this.deviceType = deviceType;
		this.meterModel = meterModel;
		this.firmwareVersion = firmwareVersion;
		this.meterConnectionType = meterConnectionType;
		this.valveStatus = valveStatus;
		this.deviceRtc = deviceRtc;
		this.pulseCount = pulseCount;
		this.batteryVoltage = batteryVoltage;
		this.pendingEvent = pendingEvent;
	
		this.lastUpdateTime = lastUpdateTime;
		this.gatewayId = gatewayId;
		this.gatewayEui = gatewayEui;
		this.devEui = devEui;
		this.rechargeStatus = rechargeStatus;
		this.creditLimit01 = creditLimit01;
		this.creditLimit02 = creditLimit02;
		this.leakDetector1 = leakDetector1;
		this.leakDetector2 = leakDetector2;
		this.perLitrePrice = perLitrePrice;
		this.availableAmount = availableAmount;
		this.batteryVoltageCuttOff = batteryVoltageCuttOff;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private String slNo;

    @Column(name = "command_id")
    private String commandId;

    @Column(name = "reserved_byte")
    private String reservedByte;

    @Column(name = "network_type")
    private String networkType;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "meter_model")
    private String meterModel;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "meter_connection_type")
    private String meterConnectionType;

    @Column(name = "valve_status")
    private String valveStatus;

    @Column(name = "device_rtc")
    private String deviceRtc;

    @Column(name = "pulse_count")
    private String pulseCount;

    @Column(name = "battery_voltage")
    private String batteryVoltage;

    @Column(name = "pending_event")
    private String pendingEvent;


    @Column(name = "last_update_time")
    private String lastUpdateTime;

    // --- NEW FIELDS FROM DATABASE ---
    @Column(name = "gateway_id")
    private String gatewayId;

    @Column(name = "gateway_eui")
    private String gatewayEui;

    @Column(name = "dev_eui")
    private String devEui;

    @Column(name = "recharge_status")
    private String rechargeStatus;

    @Column(name = "credit_limit_01")
    private String creditLimit01;

    @Column(name = "credit_limit_02")
    private String creditLimit02;

    @Column(name = "leak_detector_1")
    private String leakDetector1;

    @Column(name = "leak_detector_2")
    private String leakDetector2;

    @Column(name = "per_litre_price")
    private String perLitrePrice;

    @Column(name = "available_amount")
    private String availableAmount;

    @Column(name = "battery_voltage_cutt_off")
    private String batteryVoltageCuttOff;

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getReservedByte() {
		return reservedByte;
	}

	public void setReservedByte(String reservedByte) {
		this.reservedByte = reservedByte;
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

	public String getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public String getPendingEvent() {
		return pendingEvent;
	}

	public void setPendingEvent(String pendingEvent) {
		this.pendingEvent = pendingEvent;
	}



	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getGatewayEui() {
		return gatewayEui;
	}

	public void setGatewayEui(String gatewayEui) {
		this.gatewayEui = gatewayEui;
	}

	public String getDevEui() {
		return devEui;
	}

	public void setDevEui(String devEui) {
		this.devEui = devEui;
	}

	public String getRechargeStatus() {
		return rechargeStatus;
	}

	public void setRechargeStatus(String rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

	public String getCreditLimit01() {
		return creditLimit01;
	}

	public void setCreditLimit01(String creditLimit01) {
		this.creditLimit01 = creditLimit01;
	}

	public String getCreditLimit02() {
		return creditLimit02;
	}

	public void setCreditLimit02(String creditLimit02) {
		this.creditLimit02 = creditLimit02;
	}

	public String getLeakDetector1() {
		return leakDetector1;
	}

	public void setLeakDetector1(String leakDetector1) {
		this.leakDetector1 = leakDetector1;
	}

	public String getLeakDetector2() {
		return leakDetector2;
	}

	public void setLeakDetector2(String leakDetector2) {
		this.leakDetector2 = leakDetector2;
	}

	public String getPerLitrePrice() {
		return perLitrePrice;
	}

	public void setPerLitrePrice(String perLitrePrice) {
		this.perLitrePrice = perLitrePrice;
	}

	public String getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}

	public String getBatteryVoltageCuttOff() {
		return batteryVoltageCuttOff;
	}

	public void setBatteryVoltageCuttOff(String batteryVoltageCuttOff) {
		this.batteryVoltageCuttOff = batteryVoltageCuttOff;
	}
}