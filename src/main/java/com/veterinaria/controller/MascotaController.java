package com.veterinaria.controller;

import com.veterinaria.domain.Mascota;
import com.veterinaria.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/mascota/listado")
    public String listado(Model model) {
        model.addAttribute("mascotas",
                mascotaService.listarMascotas());
        return "mascota/listado";
    }

    @GetMapping("/mascota/nuevo")
    public String nuevaMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "mascota/formulario";
    }

    @PostMapping("/mascota/guardar")
    public String guardarMascota(
            @ModelAttribute Mascota mascota) {

        mascotaService.guardarMascota(mascota);
        return "redirect:/mascota/listado";
    }

    @GetMapping("/mascota/editar/{id}")
    public String editarMascota(
            @PathVariable Long id, Model model) {

        Mascota mascota = mascotaService.buscarMascota(id);

        if (mascota == null) {
            return "redirect:/mascota/listado";
        }

        model.addAttribute("mascota", mascota);
        return "mascota/formulario";
    }   

    @PostMapping("/mascota/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/mascota/listado";
    }    
}
