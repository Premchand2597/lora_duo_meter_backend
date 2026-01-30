package com.loraDuoMeter.DTO;

public class PaymentBill_Dto {
	private String gatewayId;
	private String meterSlNo;
	private String amount;
	
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getMeterSlNo() {
		return meterSlNo;
	}
	public void setMeterSlNo(String meterSlNo) {
		this.meterSlNo = meterSlNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PaymentBill_Dto [gatewayId=" + gatewayId + ", meterSlNo=" + meterSlNo + ", amount=" + amount + "]";
	}
}
