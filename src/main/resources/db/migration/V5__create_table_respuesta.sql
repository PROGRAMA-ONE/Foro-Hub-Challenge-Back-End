CREATE TABLE IF NOT EXISTS respuesta (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  mensaje TEXT NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  solucion BOOLEAN NOT NULL DEFAULT FALSE,
  autor_id BIGINT NOT NULL,
  topico_id BIGINT NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT fk_respuesta_usuario
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_respuesta_topico
    FOREIGN KEY (topico_id) REFERENCES topico(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  INDEX idx_respuesta_topico (topico_id),
  INDEX idx_respuesta_autor (autor_id),
  INDEX idx_respuesta_solucion (solucion),
  INDEX idx_respuesta_fecha (fecha_creacion)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;