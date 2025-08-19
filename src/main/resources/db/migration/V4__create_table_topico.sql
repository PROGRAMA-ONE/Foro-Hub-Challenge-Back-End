CREATE TABLE IF NOT EXISTS topico (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status BOOLEAN NOT NULL DEFAULT TRUE,
  autor_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT fk_topico_usuario
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_topico_curso
    FOREIGN KEY (curso_id) REFERENCES curso(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  INDEX idx_topico_autor (autor_id),
  INDEX idx_topico_curso (curso_id),
  INDEX idx_topico_status (status),
  INDEX idx_topico_fecha (fecha_creacion)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;