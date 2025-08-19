-- Usuarios con contraseña en BCrypt ("123456")
INSERT INTO usuario (nombre, correo_electronico, contrasena, activo) VALUES
('Usuario Uno',   'usuario1@gmail.com', '$2a$10$PVDG0MFiGjoZXhQkbJVliedPVERlrc5iibTx.EX.CETyJdoJbKMgS', TRUE),
('Usuario Dos',   'usuario2@gmail.com', '$2a$10$PVDG0MFiGjoZXhQkbJVliedPVERlrc5iibTx.EX.CETyJdoJbKMgS', TRUE),
('Usuario Tres',  'usuario3@gmail.com', '$2a$10$PVDG0MFiGjoZXhQkbJVliedPVERlrc5iibTx.EX.CETyJdoJbKMgS', TRUE),
('Usuario Cuatro','usuario4@gmail.com', '$2a$10$PVDG0MFiGjoZXhQkbJVliedPVERlrc5iibTx.EX.CETyJdoJbKMgS', TRUE),
('Usuario Cinco', 'usuario5@gmail.com', '$2a$10$PVDG0MFiGjoZXhQkbJVliedPVERlrc5iibTx.EX.CETyJdoJbKMgS', TRUE)
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