package com.loraDuoMeter.Downlink_DTO;

public class MeterDownlinkDTO {

	private String devEui;
    private String payloadHex;

    public String getDevEui() {
        return devEui;
    }

    public void setDevEui(String devEui) {
        this.devEui = devEui;
    }

    public String getPayloadHex() {
        return payloadHex;
    }

    public void setPayloadHex(String payloadHex) {
        this.payloadHex = payloadHex;
    }

	public MeterDownlinkDTO(String devEui, String payloadHex) {
		super();
		this.devEui = devEui;
		this.payloadHex = payloadHex;
	}

	public MeterDownlinkDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
