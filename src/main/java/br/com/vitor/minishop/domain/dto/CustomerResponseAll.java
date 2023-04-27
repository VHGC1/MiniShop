package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomerResponseAll {
    private Integer id;
    private String firstName;
    private String lastName;
    private String CPF;
    private String phone;
    private String email;

    private double totalAmount;
    private List<CustomerOrderResponse> listOrders = new ArrayList<>();

    public CustomerResponseAll(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.CPF = customer.getCPF();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        this.listOrders = customer.getOrders().stream().map(listCustomerOrders -> new CustomerOrderResponse(listCustomerOrders)).collect(Collectors.toList());
        this.totalAmount = quantitySet();
    }

    private double quantitySet() {
        double totalQuantity = 0;

        for (CustomerOrderResponse listOrder : this.listOrders) {
            totalQuantity += listOrder.getTotalAmount();
        }
        return totalQuantity;
    }
}
