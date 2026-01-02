package com.loraDuoMeter.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loraDuoMeter.Entity.MeterDetailsOnly_Entity;

public interface MeterDetailsOnly_Repo extends JpaRepository<MeterDetailsOnly_Entity, Long>{
	@Query(nativeQuery = true, value = "select * from meter_details where meter_sl_no = :meterSlNo ")
	MeterDetailsOnly_Entity findByMeterSlNo(@Param("meterSlNo") String meterSlNo);
}
