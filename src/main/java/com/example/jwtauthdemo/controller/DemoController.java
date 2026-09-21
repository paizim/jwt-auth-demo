package com.example.jwtauthdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/user/me")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("Acesso liberado: Endpoint do usuário comum.");
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Acesso liberado: Endpoint de ADMIN.");
    }
}
