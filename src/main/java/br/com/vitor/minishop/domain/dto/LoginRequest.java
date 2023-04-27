package br.com.vitor.minishop.domain.dto;

import javax.validation.constraints.NotEmpty;

public record LoginRequest (@NotEmpty String email, @NotEmpty String password){}
