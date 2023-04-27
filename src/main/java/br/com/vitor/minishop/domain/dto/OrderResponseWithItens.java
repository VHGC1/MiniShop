package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OrderResponseWithItens {

    private Integer Id;
    private String FirstName;
    private String LastName;
    private LocalDateTime OrderDate;
    private double TotalAmount;
    private List<OrderItemResponse> items;

    public OrderResponseWithItens(CustomerOrder customerOrder) {
        this.Id = customerOrder.getId();
        this.FirstName = customerOrder.getCustomer().getFirstName();
        this.LastName = customerOrder.getCustomer().getLastName();
        this.OrderDate = customerOrder.getOrderDate();
        this.items = customerOrder.getOrderItems().stream().map(listCustomerOrders -> new OrderItemResponse(listCustomerOrders)).collect(Collectors.toList());
        this.TotalAmount = customerOrder.getTotalAmount();
    }
}
