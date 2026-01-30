package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loraDuoMeter.Entity.MeterDetailsOnly_Entity;

import jakarta.transaction.Transactional;

public interface MeterDetailsOnly_Repo extends JpaRepository<MeterDetailsOnly_Entity, Long>{
	@Query(nativeQuery = true, value = """
			select * from meter_details where meter_sl_no = :meterSlNo;
			""")
	MeterDetailsOnly_Entity findByMeterSlNo(@Param("meterSlNo") String meterSlNo);
	
	@Query(nativeQuery = true, value = """
			select * from meter_details where meter_connection_type = 'Postpaid' order by sl_no desc;
			""")
	List<MeterDetailsOnly_Entity> findMeterDetailsForPostPaid();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
			update meter_details set billing_date =:billingDate, buffer_day =:bufferDay where meter_connection_type = 'Postpaid';
			""")
	int updateBillDetailsForPostPaid(@Param("billingDate") String billingDate, @Param("bufferDay") String bufferDay);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
			update meter_details set new_meter_sl_no =:newMeterSlNo, tamper_detail =:tamperDetail, replace_reason=:replaceReason where meter_sl_no=:meterSlNo;
			""")
	int updateMeterReplacementDetail(@Param("newMeterSlNo") String newMeterSlNo, @Param("tamperDetail") String tamperDetail,
										@Param("replaceReason") String replaceReason, @Param("meterSlNo") String meterSlNo);
}
