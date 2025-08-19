package com.alura.forohub.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email
    @NotBlank
    private String correoElectronico;

    @NotBlank
    private String contrasena;

    public String getCorreoElectronico() { return correoElectronico; }
    public String getContrasena() { return contrasena; }
}
