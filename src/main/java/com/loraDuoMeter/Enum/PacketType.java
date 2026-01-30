package com.loraDuoMeter.Enum;

public enum PacketType {
	
	//Uplink - 7
	POWER_UP("80", 24),   
    DAILY_UPDATE("81", 26),
	TAMPER("83",25),
	GAS_LEAK("84",25),
    RECHARGE_OVER("85", 24),
	MANUAL_DEVICE_EVENT_NOTIFICATION("86",26),
    BATTERY_CUTTOFF("8D",24),     
    
    // Downlink(ODM) - 11    
    READ_METER_INFO("87",51),
    READ_INSTANTANEOUS_DATA("82",24),
    SET_BATTERY_LEVEL("04",4),
    SET_GAS_PER_LITRE_PRICE("05",6),
    SET_RECHARGE_AMOUNT("06",6),
    SET_METER_TYPE("07",3),
    SET_METER_PULSE_COUNT("09",6),
    CLEAR_PENDING_EVENTS("0A",3),
    SET_LEAK_DETECTOR("0D",10),
    SET_METERMODEL("0C",3),
    SET_CREDIT_LIMIT_INDICATION_LEVEL("0E",7);
	
	 private final String commandId;
	 private final int lengthBytes;

	    PacketType(String commandId, int lengthBytes) {
	        this.commandId = commandId;
	        this.lengthBytes = lengthBytes;
	    }

	    public static PacketType resolve(String hex) {
	        String cmd = hex.substring(0, 2);
	        int bytes = hex.length() / 2;

	        for (PacketType type : values()) {
	            if (type.commandId.equals(cmd) && type.lengthBytes == bytes) {
	                return type;
	            }
	        }
	        throw new IllegalArgumentException(
	            "Unknown packet: cmd=" + cmd + ", bytes=" + bytes
	        );
	    }

}
