package com.loraDuoMeter.Proj;

	public interface DailyUpdateDisplayView {
		
		 	Long getSlNo();
		    String getNetworkType();
		    String getDeviceType();
		    String getMeterConnection();
		    String getValveStatus();
		    String getDeviceRtc();
		    String getPulseCount();
		    String getBatteryVoltage();
		    String getNextUpdateTime();
		    String getPendingEvent();
		    String getRechargeStatus();
		    String getLastUpdateTime();
		    String getDevEui();
		    String getGatewayEui();
		    Integer getFPort();
		    String getReceivedAt();
		    String getMeter_connection();

		    String getBuildingName();
		    String getResidentName();
		    String getMeterModel();
	    
	}
