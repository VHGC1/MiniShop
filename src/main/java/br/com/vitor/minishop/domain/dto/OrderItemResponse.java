package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.OrderItem;
import lombok.Data;

@Data
public class OrderItemResponse {
    private Integer id;
    private double unitPrice;
    private Integer quantity;
    private String productName;
    private double TotalAmount;

    public OrderItemResponse(OrderItem item) {
        this.id = item.getId();
        this.unitPrice = item.getUnitPrice();
        this.quantity = item.getQuantity();
        this.productName = item.getProduct().getProductName();
        this.TotalAmount = item.getQuantity() * item.getUnitPrice();
    }
}
