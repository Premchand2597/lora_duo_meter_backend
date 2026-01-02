package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.DailyUpdate_Entity;

public interface DailyUpdate_Repo extends JpaRepository<DailyUpdate_Entity, Long>{
	List<DailyUpdate_Entity> findBySlNoOrderBySlNoDesc(long id);
}
