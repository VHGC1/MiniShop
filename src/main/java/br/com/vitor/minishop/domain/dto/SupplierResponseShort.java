package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Supplier;
import lombok.Data;

@Data
public class SupplierResponseShort {
    private Integer id;
    private String companyName;

    public SupplierResponseShort(Supplier supplier) {
        this.id = supplier.getId();
        this.companyName = supplier.getCompanyName();
    }
}
