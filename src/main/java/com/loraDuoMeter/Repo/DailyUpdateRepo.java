package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.DailyUpdateEntity;

@Repository
public interface DailyUpdateRepo extends JpaRepository<DailyUpdateEntity, Long>{

	// Query to check if a specific DevEUI exists for today's date
    // Assuming 'receivedAt' is stored as String 'yyyy-MM-dd HH:mm:ss'
    @Query("SELECT COUNT(d) > 0 FROM DailyUpdateEntity d WHERE d.devEui = :devEui AND d.receivedAt LIKE CONCAT(:date, '%')")
    boolean existsByDevEuiAndDate(String devEui, String date);

 // 3. Fetch Active Gateways (GatewayEUIs) for today
    @Query(value = """
            SELECT DISTINCT gateway_eui
            FROM daily_update_table
            WHERE received_at LIKE CONCAT(:date, '%')
        """, nativeQuery = true)
    List<String> findActiveGatewayEuisForDate(String date);
    
    // Fetch all unique DevEUIs that updated today (More efficient for bulk checks)
    @Query(value = """
    	    SELECT DISTINCT dev_eui
    	    FROM daily_update_table
    	    WHERE received_at LIKE CONCAT(:date, '%')
    	""", nativeQuery = true)
    	List<String> findActiveDevEuisForDate(String date);

	
}
