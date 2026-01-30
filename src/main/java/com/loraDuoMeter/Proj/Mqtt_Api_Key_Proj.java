package com.loraDuoMeter.Proj;

public class Mqtt_Api_Key_Proj {

		private String slNo;
		private String brokerIpAddress;
	    private String portNumber;
	    private String userName;
	    private String apiKey;
	    private String topicName;
	    private String gatewayId;
	    private String lastInsertedTime;
	    
		public Mqtt_Api_Key_Proj(String slNo, String brokerIpAddress, String portNumber, String userName, String apiKey,
				String topicName, String gatewayId, String lastInsertedTime) {
			super();
			this.slNo = slNo;
			this.brokerIpAddress = brokerIpAddress;
			this.portNumber = portNumber;
			this.userName = userName;
			this.apiKey = apiKey;
			this.topicName = topicName;
			this.gatewayId = gatewayId;
			this.lastInsertedTime = lastInsertedTime;
		}
		public Mqtt_Api_Key_Proj() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getSlNo() {
			return slNo;
		}
		public void setSlNo(String slNo) {
			this.slNo = slNo;
		}
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
		public String getTopicName() {
			return topicName;
		}
		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
		public String getGatewayId() {
			return gatewayId;
		}
		public void setGatewayId(String gatewayId) {
			this.gatewayId = gatewayId;
		}
		public String getLastInsertedTime() {
			return lastInsertedTime;
		}
		public void setLastInsertedTime(String lastInsertedTime) {
			this.lastInsertedTime = lastInsertedTime;
		}
		@Override
		public String toString() {
			return "Mqtt_Api_Key_Proj [slNo=" + slNo + ", brokerIpAddress=" + brokerIpAddress + ", portNumber="
					+ portNumber + ", userName=" + userName + ", apiKey=" + apiKey + ", topicName=" + topicName
					+ ", gatewayId=" + gatewayId + ", lastInsertedTime=" + lastInsertedTime + "]";
		}
		
		
	    
}
