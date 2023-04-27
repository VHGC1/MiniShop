package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Supplier;
import lombok.Data;

import java.util.List;

@Data
public class SupplierResponseAll {
    private Integer id;
    private String companyName;
    private String cnpj;
    private String email;
    private String city;
    private String uf;
    private String contactName;
    private String phone;
    private List<ProductResponseWithoutSupplier> products;


    // Usado para mapear/criar um DTO usando uma entidade
    public SupplierResponseAll(Supplier supplier) {
        this.id = supplier.getId();
        this.companyName = supplier.getCompanyName();
        this.cnpj = supplier.getCNPJ();
        this.email = supplier.getEmail();
        this.city = supplier.getCity();
        this.uf = supplier.getUF().toString();
        this.contactName = supplier.getContactName();
        this.phone = supplier.getPhone();
    }

    public SupplierResponseAll(Supplier supplier, List<ProductResponseWithoutSupplier> listOfProducts) {
        this.id = supplier.getId();
        this.companyName = supplier.getCompanyName();
        this.cnpj = supplier.getCNPJ();
        this.email = supplier.getEmail();
        this.city = supplier.getCity();
        this.uf = supplier.getUF().toString();
        this.contactName = supplier.getContactName();
        this.phone = supplier.getPhone();
        this.products = listOfProducts;
    }
}
