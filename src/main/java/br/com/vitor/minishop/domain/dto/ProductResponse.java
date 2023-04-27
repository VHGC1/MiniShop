package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Product;
import br.com.vitor.minishop.domain.entity.ProductImage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductResponse {
    private int id;
    private String productName;
    private SupplierResponse supplier;
    private double unitPrice;
    private boolean isDiscontinued;
    private String packageName;
    private List<String> images = new ArrayList<>();


    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.supplier = new SupplierResponse(product.getSupplier());
        this.unitPrice = product.getUnitPrice();
        this.isDiscontinued = product.getIsDiscontinued();
        this.packageName = product.getPackageName();

        List<ProductImage> productImages = product.getProductImages();
        if (productImages != null && !productImages.isEmpty())
            this.images = productImages.stream()
                    .map((productImage) -> productImage.getURL())
                    .collect(Collectors.toList());
    }
}
