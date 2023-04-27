package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Supplier;
import lombok.Data;

@Data
public class SupplierResponsePartial {
    private Integer id;
    private String companyName;
    private String email;
    private String city;
    private String uf;
    private String contactName;
    private String phone;

    // Usado para mapear/criar um DTO usando uma entidade
    public SupplierResponsePartial(Supplier supplier) {
        this.id = supplier.getId();
        this.companyName = supplier.getCompanyName();
        this.email = supplier.getEmail();
        this.city = supplier.getCity();
        this.uf = supplier.getUF().toString();
        this.contactName = supplier.getContactName();
        this.phone = supplier.getPhone();
    }
}
