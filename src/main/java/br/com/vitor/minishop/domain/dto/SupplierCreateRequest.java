package br.com.vitor.minishop.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class SupplierCreateRequest {
    @NotEmpty(message = "É necessário informar o nome da empresa.")
    @Size(max = 100)
    private String CompanyName;
    @NotEmpty(message = "É necessário informar o CNPJ.")
    @Size(max = 14)
    private String CNPJ;
    @Email(message = "É necessário informar o Email.")
    @Size(max = 255)
    private String Email;
    @NotEmpty(message = "É necessário informar a Cidade.")
    @Size(max = 100)
    private String City;
    @NotEmpty(message = "É necessário informar a UF.")
    @Size(max = 2)
    private String UF;
    @Size(max = 50)
    private String ContactName;
    @Size(max = 15)
    private String Phone;
}
