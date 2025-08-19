package com.alura.forohub.repository;

import com.alura.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
    boolean existsByTituloIgnoreCaseAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCurso_NombreIgnoreCase(String nombreCurso, Pageable pageable);

    Page<Topico> findByCurso_NombreIgnoreCaseAndFechaCreacionBetween(
            String nombreCurso, LocalDateTime inicio, LocalDateTime fin, Pageable pageable);

    Page<Topico> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);


}
