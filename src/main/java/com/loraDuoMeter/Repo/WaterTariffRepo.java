package com.loraDuoMeter.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.WaterTariffEntity;

@Repository
public interface WaterTariffRepo extends JpaRepository<WaterTariffEntity, Long>{

}
