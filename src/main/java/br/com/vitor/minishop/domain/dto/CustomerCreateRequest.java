package br.com.vitor.minishop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequest {
    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(max = 100)
    private String FirstName;
    @NotEmpty(message = "Sobrenome não pode ser vazio")
    @Size(max = 100)
    private String LastName;
    @NotEmpty(message = "CPF não pode ser vazio")
    @Size(max = 11)
    private String CPF;
    @Size(max = 15)
    private String Phone;
    @Email(message = "Email tem que ser válido")
    @Size(max = 255)
    private String Email;
}
