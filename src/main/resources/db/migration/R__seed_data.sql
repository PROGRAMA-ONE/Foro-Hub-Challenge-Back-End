INSERT INTO usuario (nombre, correo_electronico, contrasena, activo)
VALUES
('Usuario Uno', 'usuario1@test.com', 'bcrypt_dummy_1', TRUE),
('Usuario Dos', 'usuario2@test.com', 'bcrypt_dummy_2', TRUE),
('Usuario Tres', 'usuario3@test.com', 'bcrypt_dummy_3', TRUE),
('Usuario Cuatro', 'usuario4@test.com', 'bcrypt_dummy_4', TRUE),
('Usuario Cinco', 'usuario5@test.com', 'bcrypt_dummy_5', TRUE)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

-- Cursos
INSERT INTO curso (nombre, categoria, activo)
VALUES
('Java Básico', 'Programación', TRUE),
('Spring Boot Avanzado', 'Programación', TRUE),
('Bases de Datos MySQL', 'Base de Datos', TRUE),
('Hibernate y JPA', 'ORM', TRUE),
('APIs REST con Spring', 'Programación', TRUE),
('Patrones de Diseño', 'Arquitectura', TRUE),
('Microservicios con Spring Cloud', 'Programación', TRUE),
('Docker para Desarrolladores', 'DevOps', TRUE),
('Kubernetes Básico', 'DevOps', TRUE),
('Git y GitHub', 'Herramientas', TRUE)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);