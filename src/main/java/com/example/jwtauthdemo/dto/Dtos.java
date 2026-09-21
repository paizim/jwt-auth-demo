package com.example.jwtauthdemo.dto;

import com.example.jwtauthdemo.entity.Role;
import lombok.Data;

public class Dtos {

    @Data
    public static class RegisterRequest {
        private String username;
        private String password;
        private Role role;
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }
    }
}
