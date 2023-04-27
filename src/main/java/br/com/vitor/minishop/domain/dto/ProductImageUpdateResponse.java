package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImageUpdateResponse {

    private String url;
    private Integer sequence;

    public ProductImageUpdateResponse(ProductImage productImage) {
        this.url = productImage.getURL();
        this.sequence = productImage.getSequence();
    }
}