package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.GatewayEntity;


@Repository
public interface GatewayRepo extends JpaRepository<GatewayEntity, Long>{

	// Inside GatewayRepo interface
	List<GatewayEntity> findByBuildingId(String buildingId);
	void deleteByBuildingId(String buildingId);
	// Inside MeterRepo interface
	void deleteByGatewayId(String gatewayId);
}
