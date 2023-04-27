package br.com.vitor.minishop.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class SupplierUpdateRequest {

    private String CompanyName;
    @Size(max = 255)
    private String Email;
    @Size(max = 100)
    private String City;
    @Size(max = 2)
    private String UF;
    @Size(max = 50)
    private String ContactName;
    @Size(max = 15)
    private String Phone;
}
