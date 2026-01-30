package com.loraDuoMeter.Enum;

public enum ValveStatus {

	CLOSE("00", "Close"),
	OPEN("01", "Open");
	
	private final String hex;
    private final String label;

    ValveStatus(String hex, String label) {
        this.hex = hex;
        this.label = label;
    }

    public static String fromHex(String hex) {
        for (ValveStatus v : values()) {
            if (v.hex.equalsIgnoreCase(hex)) {
                return v.label;
            }
        }
        return "Unknown";
    }
	
}
