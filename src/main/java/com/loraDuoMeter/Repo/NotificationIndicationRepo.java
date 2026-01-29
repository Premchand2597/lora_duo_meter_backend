package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.NotificationIndicationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface NotificationIndicationRepo extends JpaRepository<NotificationIndicationEntity, Long>{
	@Query(nativeQuery = true, value = """
select coalesce(sum(case when n.power_up_notification = true then 1 else 0 end + case when n.daily_update_notification = true then 1 else 0 end + case 
when n.recharge_finish_notification = true then 1 else 0 end + case when n.battery_cut_off_notification = true then 1 else 0 end + case when 
n.tamper_event_notification = true then 1 else 0 end + case when n.gas_leak_notification = true then 1 else 0 end + case when 
n.manual_click_device_event_notification = true then 1 else 0 end + case when n.meter_info_notification = true then 1 else 0 end + case when 
n.instantaneous_data_notification = true then 1 else 0 end), 0) as total_notification_count from notification_indication_table n;
			""")
	long getTotalNotificationCount();
	
	List<NotificationIndicationEntity> findAllByOrderBySlNoDesc();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set power_up_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForPowerUp(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set daily_update_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForDailyUpdate(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set recharge_finish_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForRechargeFinish(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set battery_cut_off_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForBatteryCutOff(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set tamper_event_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForTamperEvent(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set gas_leak_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForGasLeak(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set manual_click_device_event_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForManualClickDevice(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set meter_info_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForMeterInfo(@Param("meterSlNo") String meterSlNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update notification_indication_table set instantaneous_data_notification = false where dev_eui = :meterSlNo;
			""")
	void updateNotificationTableForInstantaneousData(@Param("meterSlNo") String meterSlNo);
}
