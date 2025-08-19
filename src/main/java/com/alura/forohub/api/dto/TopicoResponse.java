package com.alura.forohub.api.dto;

import java.time.LocalDateTime;

public class TopicoResponse {
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean status;
    private Long autorId;
    private Long cursoId;

    public TopicoResponse(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, boolean status, Long autorId, Long cursoId) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autorId = autorId;
        this.cursoId = cursoId;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public boolean isStatus() { return status; }
    public Long getAutorId() { return autorId; }
    public Long getCursoId() { return cursoId; }
}
