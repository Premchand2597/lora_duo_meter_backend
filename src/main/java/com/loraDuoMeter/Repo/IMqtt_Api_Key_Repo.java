package com.loraDuoMeter.Repo;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.Mqtt_Api_key_Entity;
import com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj;
import com.loraDuoMeter.Proj.Mqtt_Api_Key_Proj;

@Repository
public interface IMqtt_Api_Key_Repo extends JpaRepository<Mqtt_Api_key_Entity, String> {

	@Query("""
			Select new com.loraDuoMeter.Proj.Mqtt_Api_Key_Proj(
			m.slNo,
			m.brokerIpAddress,
			m.portNumber,
			m.userName,
			m.apiKey,
			m.topicName,
			m.gatewayId,
			m.lastInsertedTime	)
			FROM Mqtt_Api_key_Entity m
			""")
		Slice<Mqtt_Api_Key_Proj>find_all_api_keys(Pageable pageable);
		
		
		 @Query("""
			        Select new com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj(
			            m.brokerIpAddress,
			            m.portNumber,
			            m.userName,
			            m.apiKey,
			            m.gatewayId
			        )
			        FROM Mqtt_Api_key_Entity m
			        WHERE m.gatewayId = :gatewayId
			    """)
			    Optional<Mqtt_Api_Key_Down_Proj> findByGatewayId(@Param("gatewayId") String gatewayId);

}

