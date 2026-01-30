package com.loraDuoMeter.Service;

import java.util.List;

import com.loraDuoMeter.DTO.BatteryCutOff_Dto;
import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.DTO.MeterDetailsOnly_Dto;
import com.loraDuoMeter.DTO.MeterDetails_Dto;
import com.loraDuoMeter.DTO.MqttApiKey_Dto;
import com.loraDuoMeter.DTO.NotificationIndicationDto;
import com.loraDuoMeter.DTO.NotificationIndicationWithResidentDetailsDto;
import com.loraDuoMeter.DTO.RechargeFinish_Dto;
import com.loraDuoMeter.DTO.RegisterDto;
import com.loraDuoMeter.DTO.TamperEventAndMeterDetailsDto;
import com.loraDuoMeter.DTO.TamperEventsDto;
import com.loraDuoMeter.DTO.TamperGraphDto;

public interface Main_Service {
	RegisterDto addNewData(RegisterDto dto);
	List<BatteryCutOff_Dto> fetchAllBatteryDetails();
	List<MeterDetails_Dto> fetchAllMeterDetailsData();
	MeterDetails_Dto getMeterDataBasedOnId(long id);
	List<MeterDetailsBuildingName_Dto> getDistinctBuildingNames();
	List<MeterDetailsMeterSlNo_Dto> getMeterSlNoBasedOnBuildingName(String buildingName);
	MeterDetailsOnly_Dto getMeterDetailsByMeterSlNo(String meterSlNo);
	List<MeterDetailsOnly_Dto> fetchMeterDetailsForPostPaidType();
	boolean updateBillDetailsForPostPaidData(String billingDate, String bufferDay);
	List<TamperEventsDto> fetchAllTamperEventsDetail();
	boolean updateMeterReplacedDetails(String newMeterSlNo, String tamperDetail, String replaceReason, String meterSlNo);
	List<TamperEventAndMeterDetailsDto> fetchAllTamperAndMeterDetailsData();
	List<NotificationIndicationDto> fetchAllNotificationDetails();
	List<NotificationIndicationWithResidentDetailsDto> fetchAllNotificationDetailsWithResidentDetails();
	List<MqttApiKey_Dto> fetchAllMqttDetails();
	int insertApiKetDetails(MqttApiKey_Dto dto);
	List<TamperGraphDto> getTamperGraphData();
	List<RechargeFinish_Dto> getAllRechargeFinishDetails();
}
