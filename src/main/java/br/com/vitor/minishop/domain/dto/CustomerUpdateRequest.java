package br.com.vitor.minishop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Size(max = 100)
    private String FirstName;
    @Size(max = 100)
    private String LastName;
    @Size(max = 15)
    private String Phone;
    @Size(max = 255)
    private String Email;
}
