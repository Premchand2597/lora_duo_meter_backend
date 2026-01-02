package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.Entity.MeterDetails_Entity;

public interface MeterDetails_Repo extends JpaRepository<MeterDetails_Entity, Long>{
	List<MeterDetails_Entity> findByOrderBySlNoDesc();
	
	@Query(nativeQuery = true, value = """
			select distinct building_name from meter_details;
			""")
	List<MeterDetailsBuildingName_Dto> fetchDistinctBuildingName();
	
	@Query(nativeQuery = true, value = """
			select meter_sl_no from meter_details where building_name = :buildingName;
			""")
	List<MeterDetailsMeterSlNo_Dto> fetchMeterSlNoBasedOnBuildingName(@Param("buildingName") String buildingName);
}
