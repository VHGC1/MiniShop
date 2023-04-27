package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Product;
import lombok.Data;

@Data
public class ProductResponseWithoutSupplier {
    private int id;
    private String productName;
    private double unitPrice;
    private boolean isDiscontinued;
    private String packageName;

    public ProductResponseWithoutSupplier(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.unitPrice = product.getUnitPrice();
        this.isDiscontinued = product.getIsDiscontinued();
        this.packageName = product.getPackageName();
    }
}
