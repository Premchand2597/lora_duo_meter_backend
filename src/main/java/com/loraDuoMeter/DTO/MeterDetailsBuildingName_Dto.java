package com.loraDuoMeter.DTO;

public class MeterDetailsBuildingName_Dto {
	private String buildingName;

	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	@Override
	public String toString() {
		return "MeterDetailsBuildingName_Dto [buildingName=" + buildingName + "]";
	}
	public MeterDetailsBuildingName_Dto(String buildingName) {
		super();
		this.buildingName = buildingName;
	}
	public MeterDetailsBuildingName_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
