package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.domain.dto.LoginRequest;
import br.com.vitor.minishop.domain.dto.LoginResponse;
import br.com.vitor.minishop.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        LoginResponse response = loginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
