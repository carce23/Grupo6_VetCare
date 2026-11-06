
package com.veterinaria.controller;

import com.veterinaria.domain.Usuario;
import com.veterinaria.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {

        var usuarios = usuarioService.getUsuarios();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        model.addAttribute("panel", "usuarios");

        return "usuario/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        String titulo = "todoOk";
        String mensaje = "mensaje.actualizado";

        if (bindingResult.hasErrors()) {

            titulo = "error";
            mensaje = "usuario.errorDatos";

        } else {

            try {

                usuarioService.save(usuario);

            } catch (DataIntegrityViolationException e) {

                titulo = "error";
                mensaje = "usuario.error04";

            } catch (IllegalArgumentException e) {

                titulo = "error";
                mensaje = "usuario.error01";
            }
        }

        redirectAttributes.addFlashAttribute(
                titulo,
                messageSource.getMessage(
                        mensaje, null, Locale.getDefault()));

        return "redirect:/usuario/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam Long idUsuario,
            RedirectAttributes redirectAttributes) {

        String titulo = "todoOk";
        String mensaje = "mensaje.eliminado";

        try {

            usuarioService.delete(idUsuario);

        } catch (IllegalArgumentException e) {

            titulo = "error";
            mensaje = "usuario.error01";

        } catch (IllegalStateException e) {

            titulo = "error";
            mensaje = "usuario.error02";
        }

        redirectAttributes.addFlashAttribute(
                titulo,
                messageSource.getMessage(
                        mensaje, null, Locale.getDefault()));

        return "redirect:/usuario/listado";
    }
}
