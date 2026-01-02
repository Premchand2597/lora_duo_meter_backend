package com.loraDuoMeter.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.RefreshToken_Entity;

public interface RefreshToken_Repo extends JpaRepository<RefreshToken_Entity, Integer>{
	RefreshToken_Entity findByToken(String token);
}
