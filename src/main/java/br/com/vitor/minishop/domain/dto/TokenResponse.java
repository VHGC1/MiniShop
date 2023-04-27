package br.com.vitor.minishop.domain.dto;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;
    private String email;

    public TokenResponse(String token, LoginRequest loginRequest) {
        this.token = token;
        this.email = loginRequest.email();
    }
}
