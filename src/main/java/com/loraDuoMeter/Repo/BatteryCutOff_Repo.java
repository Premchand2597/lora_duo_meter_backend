package com.loraDuoMeter.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.BatteryCutOff_Entity;

public interface BatteryCutOff_Repo extends JpaRepository<BatteryCutOff_Entity, String>{
	List<BatteryCutOff_Entity> findByOrderBySlNoDesc();
}
