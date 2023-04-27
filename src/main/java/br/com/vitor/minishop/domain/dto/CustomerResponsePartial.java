package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Customer;
import lombok.Data;

@Data
public class CustomerResponsePartial {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    public CustomerResponsePartial(Customer customer){
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
    }
}
