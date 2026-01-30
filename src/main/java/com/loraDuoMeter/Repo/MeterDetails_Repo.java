package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.Entity.MeterDetails_Entity;

import jakarta.transaction.Transactional;

public interface MeterDetails_Repo extends JpaRepository<MeterDetails_Entity, Long>{

	
List<MeterDetails_Entity> findByOrderBySlNoDesc();
	
	@Query(nativeQuery = true, value = """
			select distinct building_name from meter_details where building_name is not null;
			""")
	List<MeterDetailsBuildingName_Dto> fetchDistinctBuildingName();
	
	@Query(nativeQuery = true, value = """
			select meter_sl_no from meter_details where building_name = :buildingName;
			""")
	List<MeterDetailsMeterSlNo_Dto> fetchMeterSlNoBasedOnBuildingName(@Param("buildingName") String buildingName);
	
	// 1. Update Building Name (Cascading)
    @Modifying
    @Transactional
    @Query("UPDATE MeterEntity m SET m.buildingName = :newName WHERE m.buildingId = :buildingId")
    void updateBuildingName(@Param("buildingId") String buildingId, @Param("newName") String newName);

    // 2. Update Resident Details (Cascading)
    // CRITICAL: We now use resident_id to find the meter, and update the name/type
    @Modifying
    @Transactional
    @Query("UPDATE MeterEntity m SET m.residentName = :resName, m.residentType = :resType WHERE m.residentId = :resId")
    void updateResidentDetails(@Param("resId") String resId, @Param("resName") String resName, @Param("resType") String resType);
}
