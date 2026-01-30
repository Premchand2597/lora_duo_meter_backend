package com.loraDuoMeter.Service;

import java.util.List;

import com.loraDuoMeter.DTO.BatteryCutOff_Dto;
import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.DTO.MeterDetailsOnly_Dto;
import com.loraDuoMeter.DTO.MeterDetails_Dto;
import com.loraDuoMeter.DTO.RegisterDto;
import com.loraDuoMeter.DTO.TamperGraphDto;
import com.loraDuoMeter.Entity.OdmInstantaneousEntity;
import com.loraDuoMeter.Entity.OdmMeterInfoEntity;

public interface Main_Service {
	RegisterDto addNewData(RegisterDto dto);
	List<BatteryCutOff_Dto> fetchAllBatteryDetails();
	List<MeterDetails_Dto> fetchAllMeterDetailsData();
	MeterDetails_Dto getMeterDataBasedOnId(long id);
	List<MeterDetailsBuildingName_Dto> getDistinctBuildingNames();
	List<MeterDetailsMeterSlNo_Dto> getMeterSlNoBasedOnBuildingName(String buildingName);
	MeterDetailsOnly_Dto getMeterDetailsByMeterSlNo(String meterSlNo);
	List<TamperGraphDto> getTamperGraphData();
	List<OdmMeterInfoEntity> getOdmMeterInfo();
	List<OdmInstantaneousEntity> getInstantaneousData();
}
