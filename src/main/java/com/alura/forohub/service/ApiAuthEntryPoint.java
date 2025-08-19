package com.alura.forohub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiAuthEntryPoint implements AuthenticationEntryPoint{
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String reason = (String) request.getAttribute("auth_error");
        if (reason == null) reason = "TOKEN_MISSING";

        String message = switch (reason) {
            case "TOKEN_EXPIRED" -> "El token ha expirado.";
            case "TOKEN_INVALID" -> "El token es inválido o está mal formado.";
            case "TOKEN_USERNAME_MISMATCH" -> "El token no corresponde al usuario autenticado.";
            case "TOKEN_VERIFICATION_ERROR" -> "No fue posible verificar el token.";
            case "TOKEN_MISSING" -> "Token no enviado. Use el encabezado Authorization: Bearer <token>.";
            default -> "No autorizado.";
        };

        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        body.put("error", reason);
        body.put("path", request.getRequestURI());
        body.put("status", 401);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), body);
    }
}
