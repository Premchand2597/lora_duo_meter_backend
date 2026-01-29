package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.TamperEventAndMeterDetails_Entity;

@Repository
public interface TamperEventAndMeterDetails_Repo extends JpaRepository<TamperEventAndMeterDetails_Entity, Long>{
	@Query(nativeQuery = true, value = """
select t.*, m.meter_sl_no as meter_details_meter_sl_no, m.new_meter_sl_no as replaced_meter_sl_no, m.tamper_detail, m.replace_reason, 
m.building_name, m.resident_type, m.resident_name, m.device_type as meter_details_device_type, m.meter_connection_type as 
meter_details_meter_connection_type, m.date_time as meter_details_date_time from tamper_event_table t inner join meter_details m on 
m.meter_sl_no = t.dev_eui order by t.sl_no desc;
			""")
	List<TamperEventAndMeterDetails_Entity> fetchTamperAndMeterDetailsData();
}
