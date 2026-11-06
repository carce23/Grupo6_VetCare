package com.veterinaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    @NotBlank
    @Email
    @Size(max = 150)
    private String correo;

    @Column(length = 255)
    private String contrasena;

    @Column(nullable = false, length = 30)
    @NotBlank
    private String rol;

    @Column(nullable = false)
    private boolean estado = true;
}
