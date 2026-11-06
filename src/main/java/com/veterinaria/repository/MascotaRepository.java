package com.veterinaria.repository;

import com.veterinaria.domain.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

}
