package com.loraDuoMeter.Enum;

public enum ReservedByte {
	
	RECHARGE_ACK("01", "Recharge ACK"),
    RESERVED(null, "Reserved");

    private final String hex;
    private final String label;

    ReservedByte(String hex, String label) {
        this.hex = hex;
        this.label = label;
    }

    public static String fromHex(String hex) {
        if ("01".equalsIgnoreCase(hex)) {
            return RECHARGE_ACK.label;
        }

        int value = Integer.parseInt(hex, 16);
        if (value >= 0x02 && value <= 0xFF) {
            return RESERVED.label;
        }

        return "Invalid";
    }

}
