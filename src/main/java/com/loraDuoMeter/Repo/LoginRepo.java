package com.loraDuoMeter.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loraDuoMeter.Entity.LoginEntity;

public interface LoginRepo extends JpaRepository<LoginEntity, Integer>{
	boolean existsByEmail(String email);
	Optional<LoginEntity> findByEmail(String email);
}
