package com.loraDuoMeter.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.loraDuoMeter.Entity.LoginEntity;

import jakarta.transaction.Transactional;

public interface LoginRepo extends JpaRepository<LoginEntity, Integer>{
	boolean existsByEmail(String email);
	Optional<LoginEntity> findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	update login_details set otp=:otp where email=:email;
			""")
	void updateOtpBasedOnEmail(String otp, String email);
}
