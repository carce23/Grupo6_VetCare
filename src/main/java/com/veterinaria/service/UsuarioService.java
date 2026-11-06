
package com.veterinaria.service;

import com.veterinaria.domain.Usuario;
import com.veterinaria.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Transactional
    public void save(Usuario usuario) {

        if (usuario.getIdUsuario() == null) {

            usuario.setContrasena(null);
            usuarioRepository.saveAndFlush(usuario);

        } else {

            Usuario existente = usuarioRepository.findById(
                    usuario.getIdUsuario()).orElseThrow(
                    () -> new IllegalArgumentException(
                            "El usuario no existe."));

            existente.setNombre(usuario.getNombre());
            existente.setCorreo(usuario.getCorreo());
            existente.setRol(usuario.getRol());
            existente.setEstado(usuario.isEstado());

            usuarioRepository.saveAndFlush(existente);
        }
    }

    @Transactional
    public void delete(Long idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {
            throw new IllegalArgumentException(
                    "El usuario no existe.");
        }

        try {

            usuarioRepository.deleteById(idUsuario);
            usuarioRepository.flush();

        } catch (DataIntegrityViolationException e) {

            throw new IllegalStateException(
                    "No se puede eliminar el usuario porque "
                    + "tiene registros asociados.", e);
        }
    }
}
