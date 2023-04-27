package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomerOrderResponse {
    private Integer id;
    private LocalDateTime orderDate;
    private double totalAmount;

    private String firstName;

    private String lastName;

    public CustomerOrderResponse(CustomerOrder customerOrder) {
        id = customerOrder.getId();
        orderDate = customerOrder.getOrderDate();
        totalAmount = customerOrder.getTotalAmount();

        firstName = customerOrder.getCustomer().getFirstName();
        lastName = customerOrder.getCustomer().getLastName();
    }
}
