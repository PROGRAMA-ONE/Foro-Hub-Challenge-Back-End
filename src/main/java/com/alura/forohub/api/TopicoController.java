package com.alura.forohub.api;

import com.alura.forohub.api.dto.TopicoCreateRequest;
import com.alura.forohub.api.dto.TopicoResponse;
import com.alura.forohub.api.dto.TopicoUpdateRequest;
import com.alura.forohub.model.Topico;
import com.alura.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<TopicoResponse> crear(@Valid @RequestBody TopicoCreateRequest request) {
        Topico topico = topicoService.crear(request.getTitulo(), request.getMensaje(), request.getAutorId(), request.getCursoId());
        TopicoResponse body = new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
        return ResponseEntity.created(URI.create("/topicos/" + topico.getId())).body(body);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TopicoResponse>> listar(
            @RequestParam(value = "curso", required = false) String nombreCurso,
            @RequestParam(value = "anio", required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable
    ) {
        Page<Topico> page = topicoService.listar(nombreCurso, anio, pageable);
        Page<TopicoResponse> dto = page.map(t -> new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.isStatus(),
                t.getAutor().getId(),
                t.getCurso().getId()
        ));
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicoResponse> detalle(@PathVariable Long id) {
        Topico t = topicoService.obtenerPorId(id);
        TopicoResponse body = new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.isStatus(),
                t.getAutor().getId(),
                t.getCurso().getId()
        );
        return ResponseEntity.ok(body);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<TopicoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody TopicoUpdateRequest request) {
        Topico t = topicoService.actualizar(id, request.getTitulo(), request.getMensaje(), request.getCursoId());
        TopicoResponse body = new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.isStatus(),
                t.getAutor().getId(),
                t.getCurso().getId()
        );
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        Map<String, String> body = new HashMap<>();
        body.put("message", "Tópico eliminado correctamente (id=" + id + ")");
        return ResponseEntity.ok(body);
    }
}