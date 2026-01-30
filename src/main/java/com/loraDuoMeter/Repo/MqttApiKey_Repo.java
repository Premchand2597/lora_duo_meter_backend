package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.MqttApiKey_Entity;

public interface MqttApiKey_Repo extends JpaRepository<MqttApiKey_Entity, Long>{
	List<MqttApiKey_Entity> findAllByOrderBySlNoDesc();
}
