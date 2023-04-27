package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Product;
import br.com.vitor.minishop.domain.entity.ProductImage;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponsePartial {
    private int id;
    private String productName;
    private double unitPrice;
    private boolean isDiscontinued;
    private String mainImage;

    public ProductResponsePartial(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.unitPrice = product.getUnitPrice();
        this.isDiscontinued = product.getIsDiscontinued();

        List<ProductImage> productImages = product.getProductImages();
        if (productImages != null && !productImages.isEmpty())
            this.mainImage = productImages
                    .stream().filter((productImage) -> productImage.getSequence() == 1)
                    .findFirst().get().getURL();
    }
}
