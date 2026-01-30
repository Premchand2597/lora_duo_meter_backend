package com.loraDuoMeter.Proj;

public interface IGasLeakDisplayView {
	
	
	Long getSlNo();
    String getNetworkType();
    String getDeviceType();
    String getMeterConnectionType();
    String getMeterModel();
    String getValveStatus();
    String getDeviceRtc();
    String getPulseCount();
    String getBatteryVoltage();
    String getNextUpdateTime();
    String getFlowType();
    String getLastUpdateTime();
    String getDevEui();
    String getGatewayEui();
    Integer getFPort();
    String getReceivedAt();

    String getBuildingName();
    String getResidentName();

}
