package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierResponse {
    private Integer id;
    private String companyName;
    private String cnpj;
    private String email;
    private String city;
    private String uf;
    private String contactName;
    private String phone;

    // Usado para mapear/criar um DTO usando uma entidade
    public SupplierResponse(Supplier supplier) {
        this.id = supplier.getId();
        this.companyName = supplier.getCompanyName();
        this.cnpj = supplier.getCNPJ();
        this.email = supplier.getEmail();
        this.city = supplier.getCity();
        this.uf = supplier.getUF().toString();
        this.contactName = supplier.getContactName();
        this.phone = supplier.getPhone();
    }
}
