package com.loraDuoMeter.Proj;

public class Mqtt_Api_Key_Down_Proj {
	
	private String brokerIpAddress;
    private String portNumber;
    private String userName;
    private String apiKey;
    private String gatewayId;
    
    
	public String getBrokerIpAddress() {
		return brokerIpAddress;
	}
	public void setBrokerIpAddress(String brokerIpAddress) {
		this.brokerIpAddress = brokerIpAddress;
	}
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	
	public Mqtt_Api_Key_Down_Proj(String brokerIpAddress, String portNumber, String userName, String apiKey,
			String gatewayId) {
		super();
		this.brokerIpAddress = brokerIpAddress;
		this.portNumber = portNumber;
		this.userName = userName;
		this.apiKey = apiKey;
		this.gatewayId = gatewayId;
	}
	public Mqtt_Api_Key_Down_Proj() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mqtt_Api_Key_Down_Proj [brokerIpAddress=" + brokerIpAddress + ", portNumber=" + portNumber
				+ ", userName=" + userName + ", apiKey=" + apiKey + ", gatewayId=" + gatewayId + "]";
	}

    
    

    
    

}
