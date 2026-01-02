package com.loraDuoMeter.Repo;
import com.loraDuoMeter.Entity.ResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepo extends JpaRepository<ResidentEntity, Long>{

	
	void deleteByBuildingId(String buildingId);
	
}
