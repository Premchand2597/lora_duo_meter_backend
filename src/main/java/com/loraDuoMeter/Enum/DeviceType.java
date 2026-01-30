package com.loraDuoMeter.Enum;

public enum DeviceType {
	
	GAS("00", "Smart Gas Meter"),
    WATER("01", "Smart Water Meter");

    private final String hex;
    private final String label;

    DeviceType(String hex, String label) {
        this.hex = hex;
        this.label = label;
    }

    public static String fromHex(String hex) {
        for (DeviceType d : values()) {
            if (d.hex.equalsIgnoreCase(hex)) {
                return d.label;
            }
        }
        return "Unknown";
    }

}
