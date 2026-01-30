package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.NotificationIndicationWithResidentDetailsEntity;

@Repository
public interface NotificationIndicationWithResidentDetailsRepo extends JpaRepository<NotificationIndicationWithResidentDetailsEntity, Long>{
	
	@Query(nativeQuery = true, value = """
select n.*, r.resident_name, r.email, r.primary_mobile, r.secondary_mobile, r.building_name, r.resident_type, r.resident_address from 
notification_indication_table n inner join meter_details m on n.dev_eui = m.meter_sl_no inner join resident_details r on r.resident_id = 
m.resident_id order by n.sl_no desc;
			""")
	List<NotificationIndicationWithResidentDetailsEntity> findAllNotificationDetailsWithResidentDetails();
}
