package br.com.vitor.minishop.domain.dto;

import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class PaginatedSearchRequest {
    @PositiveOrZero(message = "page não pode ser menor que 0")
    private Integer page = 0;
    @Positive(message = "size não pode ser menor que 1")
    private Integer size = 9;
}
