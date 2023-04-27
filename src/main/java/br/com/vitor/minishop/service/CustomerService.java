package br.com.vitor.minishop.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.domain.entity.Customer;
import br.com.vitor.minishop.exception.CustomerCPFInvalidoException;
import br.com.vitor.minishop.exception.CustomerCPFValidationCaelumException;
import br.com.vitor.minishop.exception.CustomerEmaiInvalidoException;
import br.com.vitor.minishop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public ResponseBase<CustomerResponse> createCustomer(CustomerCreateRequest novo) {

        isCPFNotPresent(novo.getCPF());
        isCPF(novo.getCPF());
        isEmailNotPresent(novo.getEmail());

        Customer modeloDb = new Customer();
        modeloDb.setFirstName(novo.getFirstName());
        modeloDb.setLastName(novo.getLastName());
        modeloDb.setCPF(novo.getCPF());
        modeloDb.setPhone(novo.getPhone());
        modeloDb.setEmail(novo.getEmail());

        Customer customerSalvo = customerRepository.save(modeloDb);

        CustomerResponse customerResponse = new CustomerResponse(customerSalvo);
        return new ResponseBase<>(customerResponse);
    }
    public ResponseBase<CustomerResponseAll> getCustomerByID(int id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado"));
        var totalSpent = customerRepository.totalSpentById(id).orElse(0D);

        return new ResponseBase<>(new CustomerResponseAll(customer));
    }
    public ResponseBase<Page<CustomerResponsePartial>> getCustomer(PaginatedSearchRequest page) {
        PageRequest pagination = PageRequest.of(page.getPage(), page.getSize());
        Page<Customer> customerPage = customerRepository.findAll(pagination);
        Page<CustomerResponsePartial> customerResPage = customerPage.map(CustomerResponsePartial::new);

        return new ResponseBase<>(customerResPage);
    }
    public ResponseBase<CustomerResponse> updateCustomerById(CustomerUpdateRequest body, int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado"));

        if(!customer.getEmail().equals(body.getEmail())){
            isEmailNotPresent(body.getEmail());
        }

        customer.setFirstName(body.getFirstName());
        customer.setLastName(body.getLastName());
        customer.setEmail(body.getEmail());
        customer.setPhone(body.getPhone());

        customerRepository.save(customer);

        return new ResponseBase<>(new CustomerResponse(customer));
    }
    public void isCPF(String cpf) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf);
        }catch (InvalidStateException e){
            throw new CustomerCPFValidationCaelumException("Não é possivel digitar um CPF invalido.");
        }
    }
    public void isCPFNotPresent(String cpf){
        Optional<Customer> optionalCPFCustomer = customerRepository.findByCPF(cpf);
        if(optionalCPFCustomer.isPresent()){
            throw new CustomerCPFInvalidoException("Não é possivel digitar um CPF já existente.");
        }
    }
    public void isEmailNotPresent(String email){
        Optional<Customer> optionalEmailCustomer = customerRepository.findByEmail(email);
        if(optionalEmailCustomer.isPresent()){
            throw new CustomerEmaiInvalidoException("Não é possivel digitar um Email já existente.");
        }
    }
}
