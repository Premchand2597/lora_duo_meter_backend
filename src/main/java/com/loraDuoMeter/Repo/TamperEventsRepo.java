package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.TamperEventsEntity;

public interface TamperEventsRepo extends JpaRepository<TamperEventsEntity, Long>{
	List<TamperEventsEntity> findAllByOrderBySlNoDesc();
}
