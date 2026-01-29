package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.TamperEventEntity;

@Repository
public interface TamperEventRepo extends JpaRepository<TamperEventEntity, Long>{
	// Custom query to match your SQL request (Ordered by sl_no ASC)
    List<TamperEventEntity> findAllByOrderBySlNoAsc();
    
    // Optional: Find by Device Type
    List<TamperEventEntity> findByDeviceType(String deviceType);
}
