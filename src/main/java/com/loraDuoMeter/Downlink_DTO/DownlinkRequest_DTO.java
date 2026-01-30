package com.loraDuoMeter.Downlink_DTO;

import java.util.List;

public class DownlinkRequest_DTO {


	    private String gatewayId;
	    private List<MeterDownlinkDTO> meters;

	    public String getGatewayId() {
	        return gatewayId;
	    }

	    public void setGatewayId(String gatewayId) {
	        this.gatewayId = gatewayId;
	    }

	    public List<MeterDownlinkDTO> getMeters() {
	        return meters;
	    }

	    public void setMeters(List<MeterDownlinkDTO> meters) {
	        this.meters = meters;
	    }

		public DownlinkRequest_DTO(String gatewayId, List<MeterDownlinkDTO> meters) {
			super();
			this.gatewayId = gatewayId;
			this.meters = meters;
		}

		public DownlinkRequest_DTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	    
	}

	    

