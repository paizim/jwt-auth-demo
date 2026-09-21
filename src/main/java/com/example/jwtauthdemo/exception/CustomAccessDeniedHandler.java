package com.example.jwtauthdemo.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String jsonResponse = """
            {
                "timestamp": "%s",
                "status": 403,
                "error": "Acesso Negado",
                "message": "Você não tem permissão para acessar este recurso.",
                "path": "%s"
            }
            """.formatted(java.time.Instant.now(), request.getRequestURI());

        response.getWriter().write(jsonResponse);
    }
}
