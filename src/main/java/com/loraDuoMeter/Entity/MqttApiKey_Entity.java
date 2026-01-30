package com.loraDuoMeter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mqtt_api_key_table")
public class MqttApiKey_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private long slNo;
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
	@Column(name = "last_inserted_time")
	private String lastInsertedTime;
	@Column(name = "gateway_id")
	private String gatewayId;
	@Column(name = "created_by")
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
		return "MqttApiKey_Entity [slNo=" + slNo + ", brokerIpAddress=" + brokerIpAddress + ", portNumber=" + portNumber
				+ ", userName=" + userName + ", apiKey=" + apiKey + ", topicName=" + topicName + ", lastInsertedTime="
				+ lastInsertedTime + ", gatewayId=" + gatewayId + ", createdBy=" + createdBy + "]";
	}
}
