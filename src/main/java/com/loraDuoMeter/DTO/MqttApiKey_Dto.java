package com.loraDuoMeter.DTO;

public class MqttApiKey_Dto {

	private long slNo;
	private String brokerIpAddress;
	private String portNumber;
	private String userName;
	private String apiKey;
	private String topicName;
	private String lastInsertedTime;
	private String gatewayId;
	private String createdBy;
	
	public long getSlNo() {
		return slNo;
	}
	public void setSlNo(long slNo) {
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "MqttApiKey_Dto [slNo=" + slNo + ", brokerIpAddress=" + brokerIpAddress + ", portNumber=" + portNumber
				+ ", userName=" + userName + ", apiKey=" + apiKey + ", topicName=" + topicName + ", lastInsertedTime="
				+ lastInsertedTime + ", gatewayId=" + gatewayId + ", createdBy=" + createdBy + "]";
	}
}
