package com.veterinaria.service;

import com.veterinaria.domain.Mascota;
import com.veterinaria.repository.MascotaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    public Mascota buscarMascota(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public void guardarMascota(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }    
}
