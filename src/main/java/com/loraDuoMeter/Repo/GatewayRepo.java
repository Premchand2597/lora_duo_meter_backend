package com.loraDuoMeter.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.GatewayEntity;

import jakarta.transaction.Transactional;


@Repository
public interface GatewayRepo extends JpaRepository<GatewayEntity, Long>{
	// --- ADD THIS METHOD ---
    Optional<GatewayEntity> findByGatewayId(String gatewayId);
	// Inside GatewayRepo interface
	List<GatewayEntity> findByBuildingId(String buildingId);
	void deleteByBuildingId(String buildingId);
	// Inside MeterRepo interface
	void deleteByGatewayId(String gatewayId);
	
	@Query("SELECT g FROM GatewayEntity g WHERE g.buildingId IS NOT NULL AND g.buildingName IS NOT NULL")
    List<GatewayEntity> findValidGateways();
	
	// Update Building Name for all gateways
    @Modifying
    @Transactional
    @Query("UPDATE GatewayEntity g SET g.buildingName = :newName WHERE g.buildingId = :buildingId")
    void updateBuildingName(@Param("buildingId") String buildingId, @Param("newName") String newName);
}
