package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.RechargeFinish_Entity;

public interface RechargeFinish_Repo extends JpaRepository<RechargeFinish_Entity, Long>{
	List<RechargeFinish_Entity> findAllByOrderBySlNoDesc();
}
