package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mqtt_api_key_table")
public class Mqtt_Api_key_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private String slNo;
	 
	 @Column(name = "broker_ip_address")
	    private String brokerIpAddress;

	    @Column(name = "port_number")
	    private String portNumber;

	    @Column(name = "user_name")
	    private String userName;

	    @Column(name = "api_key")
	    private String apiKey;
	    
	    @Column(name = "topic_name")
	    private String topicName;
	    
	    @Column(name = "gateway_id")
	    private String gatewayId;

	    @Column(name = "last_inserted_time")
	    private String lastInsertedTime;

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

		public String gettopicName() {
			return topicName;
		}

		public void settopicName(String topicName) {
			this.topicName = topicName;
		}

		public String getLastInsertedTime() {
			return lastInsertedTime;
		}

		public void setLastInsertedTime(String lastInsertedTime) {
			this.lastInsertedTime = lastInsertedTime;
		}
				

		public String getGatewayId() {
			return gatewayId;
		}

		public void setGatewayId(String gatewayId) {
			this.gatewayId = gatewayId;
		}

		
		public Mqtt_Api_key_Entity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Mqtt_Api_key_Entity(String slNo, String brokerIpAddress, String portNumber, String userName,
				String apiKey, String topicName, String gatewayId, String lastInsertedTime) {
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

		@Override
		public String toString() {
			return "Mqtt_Api_key_Entity [slNo=" + slNo + ", brokerIpAddress=" + brokerIpAddress + ", portNumber="
					+ portNumber + ", userName=" + userName + ", apiKey=" + apiKey + ", topicName=" + topicName
					+ ", gatewayId=" + gatewayId + ", lastInsertedTime=" + lastInsertedTime + "]";
		}
	

	
	
}
