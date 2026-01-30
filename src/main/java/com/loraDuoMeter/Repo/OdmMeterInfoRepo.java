package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.OdmMeterInfoEntity;

@Repository
public interface OdmMeterInfoRepo extends JpaRepository<OdmMeterInfoEntity, String>{
	// Fetches all records sorted by last update time (descending)
    List<OdmMeterInfoEntity> findAllByOrderByLastUpdateTimeDesc();
}
