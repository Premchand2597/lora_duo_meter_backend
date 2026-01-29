package com.loraDuoMeter.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.loraDuoMeter.DTO.BatteryCutOff_Dto;
import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.DTO.MeterDetailsOnly_Dto;
import com.loraDuoMeter.DTO.MeterDetails_Dto;
import com.loraDuoMeter.DTO.MqttApiKey_Dto;
import com.loraDuoMeter.DTO.NotificationIndicationDto;
import com.loraDuoMeter.DTO.NotificationIndicationWithResidentDetailsDto;
import com.loraDuoMeter.DTO.RegisterDto;
import com.loraDuoMeter.DTO.TamperEventAndMeterDetailsDto;
import com.loraDuoMeter.DTO.TamperEventsDto;
import com.loraDuoMeter.Entity.BatteryCutOff_Entity;
import com.loraDuoMeter.Entity.LoginEntity;
import com.loraDuoMeter.Entity.MeterDetailsOnly_Entity;
import com.loraDuoMeter.Entity.MeterDetails_Entity;
import com.loraDuoMeter.Entity.MqttApiKey_Entity;
import com.loraDuoMeter.Entity.NotificationIndicationEntity;
import com.loraDuoMeter.Entity.NotificationIndicationWithResidentDetailsEntity;
import com.loraDuoMeter.Entity.TamperEventAndMeterDetails_Entity;
import com.loraDuoMeter.Entity.TamperEventsEntity;
import com.loraDuoMeter.Repo.BatteryCutOff_Repo;
import com.loraDuoMeter.Repo.LoginRepo;
import com.loraDuoMeter.Repo.MeterDetailsOnly_Repo;
import com.loraDuoMeter.Repo.MeterDetails_Repo;
import com.loraDuoMeter.Repo.MqttApiKey_Repo;
import com.loraDuoMeter.Repo.NotificationIndicationRepo;
import com.loraDuoMeter.Repo.NotificationIndicationWithResidentDetailsRepo;
import com.loraDuoMeter.Repo.TamperEventAndMeterDetails_Repo;
import com.loraDuoMeter.Repo.TamperEventsRepo;
import com.loraDuoMeter.Service.Main_Service;

@Service
public class MainServiceImpl implements Main_Service{
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	private BatteryCutOff_Repo batteryCutOff_Repo;
	
	@Autowired
	private MeterDetails_Repo meterDetails_Repo;
	
	@Autowired
	private MeterDetailsOnly_Repo meterDetailsOnly_Repo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TamperEventsRepo tamperEventsRepo;
	
	@Autowired
	private TamperEventAndMeterDetails_Repo tamperEventAndMeterDetails_Repo;
	
	@Autowired
	private NotificationIndicationRepo notificationIndicationRepo;
	
	@Autowired
	private NotificationIndicationWithResidentDetailsRepo notificationIndicationWithResidentDetailsRepo;
	
	@Autowired
	private MqttApiKey_Repo mqttApiKey_Repo;

	@Override
	public RegisterDto addNewData(RegisterDto dto) {
		boolean status = loginRepo.existsByEmail(dto.getEmail());
		if(status) {
			throw new RuntimeException("Email already exists!");
		}
		LoginEntity mappedEntity = mapper.map(dto, LoginEntity.class);
		mappedEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
		mappedEntity.setAccess_type("ROLE_User");
		mappedEntity.setCreated_by("Admin");
		LoginEntity savedEntity = loginRepo.save(mappedEntity);
		RegisterDto mappedDto = mapper.map(savedEntity, RegisterDto.class);
		return mappedDto;
	}

	@Override
	public List<BatteryCutOff_Dto> fetchAllBatteryDetails() {
		List<BatteryCutOff_Entity> datas = batteryCutOff_Repo.findByOrderBySlNoDesc();
		List<BatteryCutOff_Dto> dtoData = datas.stream().map(data->mapper.map(data, BatteryCutOff_Dto.class)).collect(Collectors.toList());
		return dtoData;
	}

	@Override
	public List<MeterDetails_Dto> fetchAllMeterDetailsData() {
		List<MeterDetails_Entity> datas = meterDetails_Repo.findByOrderBySlNoDesc();
		List<MeterDetails_Dto> dtoData = datas.stream().map((data)->mapper.map(data, MeterDetails_Dto.class)).toList();
		return dtoData;
	}

	@Override
	public MeterDetails_Dto getMeterDataBasedOnId(long id) {
		MeterDetails_Entity fetchedData = meterDetails_Repo.findById(id).orElseThrow(()->new RuntimeException("No data found for this id"));
		MeterDetails_Dto dtoData = mapper.map(fetchedData, MeterDetails_Dto.class);
		return dtoData;
	}

	@Override
	public List<MeterDetailsBuildingName_Dto> getDistinctBuildingNames() {
		return meterDetails_Repo.fetchDistinctBuildingName();
	}

	@Override
	public List<MeterDetailsMeterSlNo_Dto> getMeterSlNoBasedOnBuildingName(String buildingName) {
		return meterDetails_Repo.fetchMeterSlNoBasedOnBuildingName(buildingName);
	}

	@Override
	public MeterDetailsOnly_Dto getMeterDetailsByMeterSlNo(String meterSlNo) {
		MeterDetailsOnly_Entity entityData = meterDetailsOnly_Repo.findByMeterSlNo(meterSlNo);
		if(entityData == null) {
			throw new RuntimeException("Meter serial no does not exists!");
		}
		MeterDetailsOnly_Dto dtoData = mapper.map(entityData, MeterDetailsOnly_Dto.class);
		return dtoData;
	}

	@Override
	public List<MeterDetailsOnly_Dto> fetchMeterDetailsForPostPaidType() {
		List<MeterDetailsOnly_Entity> entityData = meterDetailsOnly_Repo.findMeterDetailsForPostPaid();
		List<MeterDetailsOnly_Dto> dtoData = entityData.stream().map(data -> mapper.map(data, MeterDetailsOnly_Dto.class)).toList();
		return dtoData;
	}

	@Override
	public boolean updateBillDetailsForPostPaidData(String billingDate, String bufferDay) {
		int count = meterDetailsOnly_Repo.updateBillDetailsForPostPaid(billingDate, bufferDay);
		if(count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TamperEventsDto> fetchAllTamperEventsDetail() {
		List<TamperEventsEntity> entities = tamperEventsRepo.findAllByOrderBySlNoDesc();
		List<TamperEventsDto> dtoList = entities.stream().map(data -> mapper.map(data, TamperEventsDto.class)).toList();
		return dtoList;
	}

	@Override
	public boolean updateMeterReplacedDetails(String newMeterSlNo, String tamperDetail, String replaceReason,
			String meterSlNo) {
		int count = meterDetailsOnly_Repo.updateMeterReplacementDetail(newMeterSlNo, tamperDetail, replaceReason, meterSlNo);
		if(count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TamperEventAndMeterDetailsDto> fetchAllTamperAndMeterDetailsData() {
		List<TamperEventAndMeterDetails_Entity> datas = tamperEventAndMeterDetails_Repo.fetchTamperAndMeterDetailsData();
		List<TamperEventAndMeterDetailsDto> dtoList = datas.stream().map(data -> mapper.map(data, TamperEventAndMeterDetailsDto.class)).toList();
		return dtoList;
	}

	@Override
	public List<NotificationIndicationDto> fetchAllNotificationDetails() {
		List<NotificationIndicationEntity> datas = notificationIndicationRepo.findAllByOrderBySlNoDesc();
		List<NotificationIndicationDto> dtoList = datas.stream().map(data -> mapper.map(data, NotificationIndicationDto.class)).toList();
		return dtoList;
	}

	@Override
	public List<NotificationIndicationWithResidentDetailsDto> fetchAllNotificationDetailsWithResidentDetails() {
		List<NotificationIndicationWithResidentDetailsEntity> datas = notificationIndicationWithResidentDetailsRepo.findAllNotificationDetailsWithResidentDetails();
		List<NotificationIndicationWithResidentDetailsDto> dtoList = datas.stream().map(data -> mapper.map(data, NotificationIndicationWithResidentDetailsDto.class)).toList();
		return dtoList;
	}

	@Override
	public List<MqttApiKey_Dto> fetchAllMqttDetails() {
		List<MqttApiKey_Entity> datas = mqttApiKey_Repo.findAllByOrderBySlNoDesc();
		List<MqttApiKey_Dto> dtoList = datas.stream().map(data -> mapper.map(data, MqttApiKey_Dto.class)).toList();
		return dtoList;
	}

	@Override
	public int insertApiKetDetails(MqttApiKey_Dto dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateTime = LocalDateTime.now().format(formatter);
		dto.setLastInsertedTime(formattedDateTime);
		MqttApiKey_Entity entityData = mapper.map(dto, MqttApiKey_Entity.class);
		MqttApiKey_Entity savedData = mqttApiKey_Repo.save(entityData);
		if(savedData != null) {
			return 1;
		}
		return 0;
	}
	
}
