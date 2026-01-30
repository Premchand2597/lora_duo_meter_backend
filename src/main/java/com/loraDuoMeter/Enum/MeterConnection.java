package com.loraDuoMeter.Enum;

public enum MeterConnection {

	POSTPAID("00", "Postpaid"),
    PREPAID("01", "Prepaid");

    private final String hex;
    private final String label;

    MeterConnection(String hex, String label) {
        this.hex = hex;
        this.label = label;
    }

    public static String fromHex(String hex) {
        for (MeterConnection m : values()) {
            if (m.hex.equalsIgnoreCase(hex)) {
                return m.label;
            }
        }
        return "Unknown";
    }
}
