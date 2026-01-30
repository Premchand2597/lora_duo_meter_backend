package com.loraDuoMeter.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.ResidentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ResidentRepo extends JpaRepository<ResidentEntity, Long>{

	
	void deleteByBuildingId(String buildingId);
	
	// Update Building Name for all residents in that building
    @Modifying
    @Transactional
    @Query("UPDATE ResidentEntity r SET r.buildingName = :newName WHERE r.buildingId = :buildingId")
    void updateBuildingName(@Param("buildingId") String buildingId, @Param("newName") String newName);
	
}
