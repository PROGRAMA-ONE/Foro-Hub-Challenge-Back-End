# Challenge Foro Hub

Sistema de gestión de foros desarrollado en **Java 17** con **Spring Boot 3**, **Spring Security** y **JWT** para autenticación.  
La aplicación permite a los usuarios autenticados crear, listar, actualizar y eliminar **tópicos**, además de gestionar la autenticación mediante tokens.

---

## 🚀 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
    - Spring Web
    - Spring Data JPA
    - Spring Security + JWT
    - Validation
    - Flyway Migration
- **MySQL 8**
- **Maven 4+**
- **Lombok**
- **IntelliJ IDEA**

---

## 📌 Funcionalidades principales

- **Autenticación y seguridad**
    - Inicio de sesión con correo electrónico y contraseña.
    - Generación de **token JWT** válido por tiempo definido.
    - Acceso protegido a endpoints de tópicos.

- **Gestión de Tópicos**
    - Crear un nuevo tópico.
    - Listar todos los tópicos (con paginación y filtros).
    - Consultar detalle de un tópico específico.
    - Actualizar información de un tópico.
    - Eliminar un tópico (borrado lógico o físico según configuración).

- **Migraciones con Flyway**
    - Creación automática de tablas (`usuarios`, `cursos`, `topicos`).
    - Seed inicial con usuarios y cursos dummy para pruebas.

---

## ⚙️ Instalación y configuración

1. **Clonar el repositorio**
```bash
   git clone https://github.com/usuario/forohub.git
   cd forohub
```
2. Configurar base de datos

Crear una base de datos en MySQL:
    ```bash
    CREATE DATABASE forohub;
    ```

3. Configurar application.properties
```bash
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/forohub?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=TU_PASSWORD
    
    spring.jpa.hibernate.ddl-auto=none
    spring.jpa.show-sql=true
    
    spring.flyway.enabled=true
    spring.flyway.locations=classpath:db/migration
    
    # JWT
    jwt.secret=mi_clave_secreta_segura
    jwt.expiration=86400000
```

4. Compilar y ejecutar
```bash
   mvn spring-boot:run
```

---

## 🔑 Autenticación
* Endpoint de login
```bash
    POST http://localhost:8080/login
```
Body JSON
```json lines
{
  "correoElectronico": "usuario@dominio.com",
  "contrasena": "123456"
}
```
* Respuesta
```json lines
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer",
  "expiresIn": 86400000
}
```
* Uso en peticiones

Agregar en headers:
```bash
  Authorization: Bearer <token>
```

---

## 📚 Endpoints principales
* POST /login → Autenticación y generación de token.
* POST /topicos → Crear un tópico.
* GET /topicos → Listar todos los tópicos (paginado).
* GET /topicos/{id} → Detalle de un tópico.
* PUT /topicos/{id} → Actualizar un tópico.
* DELETE /topicos/{id} → Eliminar un tópico.

---

👩‍💻 Author
> **Autor:** Martha Brmudez  
