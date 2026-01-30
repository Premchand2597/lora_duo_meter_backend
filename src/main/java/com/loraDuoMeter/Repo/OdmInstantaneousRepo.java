package com.loraDuoMeter.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.OdmInstantaneousEntity;

@Repository
public interface OdmInstantaneousRepo extends JpaRepository<OdmInstantaneousEntity, Long> {
 //   List<OdmInstantaneousEntity> findAllByOrderByLastUpdateTimeDesc();
    List<OdmInstantaneousEntity> findAllByOrderByLastUpdateTimeDesc();
    
}