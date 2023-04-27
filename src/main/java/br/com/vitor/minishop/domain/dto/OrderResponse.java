package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.CustomerOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private int id;
    private LocalDateTime orderDate;
    private String customerName;
    private int itemsQuantity;
    private double totalAmount;

    public OrderResponse(CustomerOrder order, String customerName, int itemsQuantity) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.customerName = customerName;
        this.itemsQuantity = itemsQuantity;
        this.totalAmount = order.getTotalAmount();
    }
}
