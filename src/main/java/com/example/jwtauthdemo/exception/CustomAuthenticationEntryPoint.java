package com.example.jwtauthdemo.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String jsonResponse = """
            {
                "timestamp": "%s",
                "status": 401,
                "error": "Não Autorizado",
                "message": "Autenticação necessária para acessar este recurso.",
                "path": "%s"
            }
            """.formatted(java.time.Instant.now(), request.getRequestURI());

        response.getWriter().write(jsonResponse);
    }
}
