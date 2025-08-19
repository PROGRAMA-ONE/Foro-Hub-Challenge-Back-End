package com.alura.forohub.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoCreateRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private Long autorId;

    @NotNull
    private Long cursoId;

    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public Long getAutorId() { return autorId; }
    public Long getCursoId() { return cursoId; }
}
