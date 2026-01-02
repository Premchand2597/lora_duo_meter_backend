package com.loraDuoMeter.DTO;

public class MeterDetailsMeterSlNo_Dto {
	private String meterSlNo;

	public String getMeterSlNo() {
		return meterSlNo;
	}

	public void setMeterSlNo(String meterSlNo) {
		this.meterSlNo = meterSlNo;
	}

	public MeterDetailsMeterSlNo_Dto(String meterSlNo) {
		super();
		this.meterSlNo = meterSlNo;
	}

	public MeterDetailsMeterSlNo_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MeterDetailsMeterSlNo_Dto [meterSlNo=" + meterSlNo + "]";
	}
	
}
