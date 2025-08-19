package com.alura.forohub.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoUpdateRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private Long cursoId;

    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public Long getCursoId() { return cursoId; }
}
