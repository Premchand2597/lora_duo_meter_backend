package com.loraDuoMeter.Enum;

public enum NetworkType {

	LORAWAN("00", "LoRaWAN"),
    LORANET("01", "LoRaNet"),
    GSM("02", "GSM"),
    NBIOT("03", "NBIOT"),
    SC_LORAWAN("04", "SC-LoRaWAN END Node");

    private final String hex;
    private final String label;

    NetworkType(String hex, String label) {
        this.hex = hex;
        this.label = label;
    }

    public static String fromHex(String hex) {
        for (NetworkType n : values()) {
            if (n.hex.equalsIgnoreCase(hex)) {
                return n.label;
            }
        }
        return "Reserved";
    }
	
}
