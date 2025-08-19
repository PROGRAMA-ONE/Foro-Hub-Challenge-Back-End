package com.alura.forohub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, length = 191, unique = true)
    private String correoElectronico;

    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    protected Usuario() {}

    public Usuario(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getContrasena() { return contrasena; }
    public boolean isActivo() { return activo; }
}
