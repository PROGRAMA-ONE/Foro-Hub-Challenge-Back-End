package com.alura.forohub.service;

import com.alura.forohub.model.Curso;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Topico crear(String titulo, String mensaje, Long autorId, Long cursoId) {
        if (topicoRepository.existsByTituloIgnoreCaseAndMensaje(titulo, mensaje)) {
            throw new IllegalArgumentException("Tópico duplicado: ya existe un registro con el mismo título y mensaje.");
        }

        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado: id=" + autorId));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado: id=" + cursoId));

        if (!autor.isActivo()) {
            throw new IllegalArgumentException("Autor inactivo: id=" + autorId);
        }
        if (!curso.isActivo()) {
            throw new IllegalArgumentException("Curso inactivo: id=" + cursoId);
        }

        Topico topico = new Topico(titulo, mensaje, autor, curso);
        return topicoRepository.save(topico);
    }

    @Transactional(readOnly = true)
    public Page<Topico> listar(String nombreCurso, Integer anio, Pageable pageable) {
        if (nombreCurso != null && !nombreCurso.isBlank() && anio != null) {
            LocalDateTime inicio = LocalDateTime.of(anio, 1, 1, 0, 0, 0);
            LocalDateTime fin = LocalDateTime.of(anio, 12, 31, 23, 59, 59);
            return topicoRepository.findByCurso_NombreIgnoreCaseAndFechaCreacionBetween(nombreCurso, inicio, fin, pageable);
        }
        if (nombreCurso != null && !nombreCurso.isBlank()) {
            return topicoRepository.findByCurso_NombreIgnoreCase(nombreCurso, pageable);
        }
        if (anio != null) {
            LocalDateTime inicio = LocalDateTime.of(anio, 1, 1, 0, 0, 0);
            LocalDateTime fin = LocalDateTime.of(anio, 12, 31, 23, 59, 59);
            return topicoRepository.findByFechaCreacionBetween(inicio, fin, pageable);
        }
        return topicoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Topico obtenerPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado: id=" + id));
    }

    @Transactional
    public Topico actualizar(Long id, String titulo, String mensaje, Long cursoId) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado: id=" + id));

        if (topicoRepository.existsByTituloIgnoreCaseAndMensaje(titulo, mensaje)
                && (!topico.getTitulo().equalsIgnoreCase(titulo) || !topico.getMensaje().equals(mensaje))) {
            throw new IllegalArgumentException("Tópico duplicado: ya existe un registro con el mismo título y mensaje.");
        }

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado: id=" + cursoId));

        if (!curso.isActivo()) {
            throw new IllegalArgumentException("Curso inactivo: id=" + cursoId);
        }

        topico = new Topico(topico.getId(), titulo, mensaje, topico.getAutor(), curso, topico.getFechaCreacion(), topico.isStatus(), topico.isActivo());
        return topicoRepository.save(topico);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Tópico no encontrado: id=" + id);
        }
        topicoRepository.deleteById(id);
    }
}