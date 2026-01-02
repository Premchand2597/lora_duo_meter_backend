package com.loraDuoMeter.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.MeterEntity;


@Repository
public interface MeterRepo extends JpaRepository<MeterEntity, Long>{
	void deleteByBuildingId(String buildingId);
	
	// Inside MeterRepo interface
	void deleteByGatewayId(String gatewayId);
}
