package com.alura.forohub.api;

import com.alura.forohub.api.dto.LoginRequest;
import com.alura.forohub.api.dto.LoginResponse;
import com.alura.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Value("${jwt.expiration}")
    private long expirationMs;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        if (request.getCorreoElectronico() == null || request.getCorreoElectronico().isBlank()
                || request.getContrasena() == null || request.getContrasena().isBlank()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Correo electrónico y contraseña son obligatorios.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getCorreoElectronico(), request.getContrasena())
            );
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = tokenService.generarToken(userDetails);

            return ResponseEntity.ok(
                    new LoginResponse(token, "Bearer", expirationMs)
            );
        } catch (BadCredentialsException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Credenciales inválidas: correo o contraseña incorrectos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
