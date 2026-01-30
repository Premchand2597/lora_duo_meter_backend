package com.loraDuoMeter.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.MeterEntity;

import jakarta.transaction.Transactional;


@Repository
public interface MeterRepo extends JpaRepository<MeterEntity, Long>{
	void deleteByBuildingId(String buildingId);
	// --- ADD THIS METHOD ---
    Optional<MeterEntity> findBySerialNo(String serialNo);
	// Inside MeterRepo interface
	void deleteByGatewayId(String gatewayId);
	
	List<MeterEntity> findByResidentId(String residentId);
	
	// 1. Update Building Name
    @Modifying
    @Transactional
    @Query("UPDATE MeterEntity m SET m.buildingName = :newName WHERE m.buildingId = :buildingId")
    void updateBuildingName(@Param("buildingId") String buildingId, @Param("newName") String newName);

    // 2. Update Resident Name & Type (When Resident is updated)
  /*  @Modifying
    @Transactional
    @Query("UPDATE MeterEntity m SET m.residentName = :resName, m.residentType = :resType WHERE m.residentId = :resId")
    void updateResidentDetails(@Param("resId") String resId, @Param("resName") String resName, @Param("resType") String resType);*/
    
    @Modifying
    @Transactional
    @Query("UPDATE MeterEntity m SET m.residentName = :name, m.residentType = :type, m.floorNo = :floor, m.flatNo = :flat WHERE m.residentId = :resId")
    void updateResidentDetails(
        @Param("resId") String residentId, 
        @Param("name") String fullName, 
        @Param("type") String residentType,
        @Param("floor") String floorNo,
        @Param("flat") String flatNo
    );
}
