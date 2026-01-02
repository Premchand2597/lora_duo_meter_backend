package com.loraDuoMeter.Impl;

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
import com.loraDuoMeter.DTO.RegisterDto;
import com.loraDuoMeter.Entity.BatteryCutOff_Entity;
import com.loraDuoMeter.Entity.LoginEntity;
import com.loraDuoMeter.Entity.MeterDetailsOnly_Entity;
import com.loraDuoMeter.Entity.MeterDetails_Entity;
import com.loraDuoMeter.Repo.BatteryCutOff_Repo;
import com.loraDuoMeter.Repo.LoginRepo;
import com.loraDuoMeter.Repo.MeterDetailsOnly_Repo;
import com.loraDuoMeter.Repo.MeterDetails_Repo;
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
	
}
