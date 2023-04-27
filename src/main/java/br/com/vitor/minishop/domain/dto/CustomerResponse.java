package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Customer;
import lombok.Data;

@Data
public class CustomerResponse {
    private Integer id;
    private String FirstName;
    private String LastName;
    private String CPF;
    private String Phone;
    private String Email;

    private double Total;

    public CustomerResponse(Customer customer){
        this.id = customer.getId();
        this.FirstName = customer.getFirstName();
        this.LastName = customer.getLastName();
        this.CPF = customer.getCPF();
        this.Phone = customer.getPhone();
        this.Email = customer.getEmail();
    }
}
