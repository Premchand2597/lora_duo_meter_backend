package com.loraDuoMeter.Repo;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loraDuoMeter.Entity.Gas_Leak_Display_Entity;
import com.loraDuoMeter.Proj.IGasLeakDisplayView;

@Repository
public interface IGas_Leak_Repo  extends JpaRepository<Gas_Leak_Display_Entity, Long> {

    @Transactional(readOnly = true)
    @Query(value = """
        SELECT
            d.sl_no             AS slNo,
            m.building_name     AS buildingName,
            m.resident_name     AS residentName,
            m.meter_model       As meterModel,
            d.network_type      AS networkType,
            d.device_type       AS deviceType,
            d.meter_connection_type  AS meterConnectionType,
            d.valve_status      AS valveStatus,
            d.device_rtc        AS deviceRtc,
            d.pulse_count       AS pulseCount,
            d.battery_voltage   AS batteryVoltage,
            d.next_update_time  AS nextUpdateTime,
            d.flow_type			As flowType,
            d.last_update_time  AS lastUpdateTime,
            d.dev_eui           AS devEui,
            d.gateway_eui       AS gatewayEui,
            d.f_port            AS fPort,
            d.received_at       AS receivedAt          
        FROM gas_leak_table d
        INNER JOIN (
            SELECT dev_eui, MAX(received_at) AS max_received
            FROM gas_leak_table
            GROUP BY dev_eui
        ) t ON d.dev_eui = t.dev_eui AND d.received_at = t.max_received
        LEFT JOIN meter_details m ON m.meter_sl_no = d.dev_eui
        ORDER BY
  (TO_TIMESTAMP(d.last_update_time, 'DD-MM-YYYY HH24:MI:SS')::date = CURRENT_DATE) DESC,
  TO_TIMESTAMP(d.last_update_time, 'DD-MM-YYYY HH24:MI:SS') DESC
        """, nativeQuery = true)
    Stream<IGasLeakDisplayView> findLatestByDevEuiNative();
    
    
    @Query(value = """
    		SELECT
    		    d.sl_no AS slNo,
    		    m.building_name AS buildingName,
    		    m.resident_name AS residentName,
    		    d.network_type AS networkType,
    		    d.device_type AS deviceType,
    		    d.meter_connection_type AS meterConnectionType,
    		    d.valve_status AS valveStatus,
    		    d.device_rtc AS deviceRtc,
    		    d.pulse_count AS pulseCount,
    		    d.battery_voltage AS batteryVoltage,
    		    d.next_update_time AS nextUpdateTime,
    		    d.flow_type			As flowType,
    		    d.last_update_time AS lastUpdateTime,
    		    d.dev_eui AS devEui,
    		    d.gateway_eui AS gatewayEui,
    		    d.f_port AS fPort,
    		    d.received_at AS receivedAt
    		FROM gas_leak_table d
    		JOIN (
    		    SELECT dev_eui, MAX(received_at) max_received
    		    FROM gas_leak_table
    		    GROUP BY dev_eui
    		) t ON d.dev_eui = t.dev_eui
    		   AND d.received_at = t.max_received
    		LEFT JOIN meter_details m ON m.meter_sl_no = d.dev_eui
    		ORDER BY d.received_at DESC
    		""",
    		countQuery = """
    		SELECT COUNT(DISTINCT dev_eui) FROM gas_leak_table
    		""",
    		nativeQuery = true)
    		Slice<IGasLeakDisplayView> findByDevEui(Pageable pageable);
    
    @Query(value = """
            SELECT
                d.sl_no             AS slNo,
                m.building_name     AS buildingName,
                m.resident_name     AS residentName,
                d.network_type      AS networkType,
                d.device_type       AS deviceType,
                d.meter_connection_type  AS meterConnectionType,
                d.valve_status      AS valveStatus,
                d.device_rtc        AS deviceRtc,
                d.pulse_count       AS pulseCount,
                d.battery_voltage   AS batteryVoltage,
                d.next_update_time  AS nextUpdateTime,
                d.flow_type			As flowType,
                d.last_update_time  AS lastUpdateTime,
                d.dev_eui           AS devEui,
                d.gateway_eui       AS gatewayEui,
                d.f_port            AS fPort,
                d.received_at       AS receivedAt
            FROM gas_leak_table d
            LEFT JOIN meter_details m ON m.meter_sl_no = d.dev_eui
            WHERE d.dev_eui = :devEui
    		ORDER BY TO_TIMESTAMP(d.received_at, 'DD-MM-YYYY HH24:MI:SS') DESC

            """,
            nativeQuery = true)
        Slice<IGasLeakDisplayView> findLatestByDevEuiAndDevEui(
                @Param("devEui") String devEui,
                Pageable pageable
        );

    @Query(value = "SELECT COUNT(*) FROM gas_leak_table d WHERE d.dev_eui = :devEui", nativeQuery = true)
    long countByDevEui(@Param("devEui") String devEui);
    
    
    @Query(value = """
    	    SELECT
    	        d.sl_no             AS slNo,
    	        m.building_name     AS buildingName,
    	        m.resident_name     AS residentName,
    	        d.network_type      AS networkType,
    	        d.device_type       AS deviceType,
    	        d.meter_connection_type  AS meterConnectionType,
    	        d.valve_status      AS valveStatus,
    	        d.device_rtc        AS deviceRtc,
    	        d.pulse_count       AS pulseCount,
    	        d.battery_voltage   AS batteryVoltage,
    	        d.next_update_time  AS nextUpdateTime,
    	        d.flow_type			As flowType,
    	        d.last_update_time  AS lastUpdateTime,
    	        d.dev_eui           AS devEui,
    	        d.gateway_eui       AS gatewayEui,
    	        d.f_port            AS fPort,
    	        d.received_at       AS receivedAt
    	    FROM gas_leak_table d
    	    LEFT JOIN meter_details m ON m.meter_sl_no = d.dev_eui
    	    WHERE d.dev_eui = :devEui
    	      AND (
    	           d.device_rtc ILIKE '%' || :search || '%'
    	        OR CAST(d.pulse_count AS TEXT) ILIKE '%' || :search || '%'
    	        OR CAST(d.battery_voltage AS TEXT) ILIKE '%' || :search || '%'
    	        OR d.valve_status ILIKE '%' || :search || '%'
    	        OR m.resident_name ILIKE '%' || :search || '%'
    	        OR m.building_name ILIKE '%' || :search || '%'
    	      )
    	    ORDER BY d.received_at DESC
    	""",
    	nativeQuery = true)
    	Slice<IGasLeakDisplayView> searchByDevEui(
    	        @Param("devEui") String devEui,
    	        @Param("search") String search,
    	        Pageable pageable
    	);
    
    @Query(value = """
    	    SELECT COUNT(*)
    	    FROM gas_leak_table d
    	    LEFT JOIN meter_details m ON m.meter_sl_no = d.dev_eui
    	    WHERE d.dev_eui = :devEui
    	      AND (
    	           d.device_rtc ILIKE '%' || :search || '%'
    	        OR CAST(d.pulse_count AS TEXT) ILIKE '%' || :search || '%'
    	        OR CAST(d.battery_voltage AS TEXT) ILIKE '%' || :search || '%'
    	        OR d.valve_status ILIKE '%' || :search || '%'
    	        OR m.resident_name ILIKE '%' || :search || '%'
    	        OR m.building_name ILIKE '%' || :search || '%'
    	      )
    	""", nativeQuery = true)
    	long countSearchByDevEui(
    	        @Param("devEui") String devEui,
    	        @Param("search") String search
    	);



}
