package com.loraDuoMeter.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loraDuoMeter.Entity.TariffEntity;

@Repository
public interface TariffRepo extends JpaRepository<TariffEntity, Long> {
    // Extending JpaRepository automatically provides methods like save(), findAll(), deleteById(), etc.
}
