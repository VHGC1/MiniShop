package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@RolesAllowed({"ROLE_BACKOFFICE_USER", "ROLE_ADMIN_USER"})
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CustomerCreateRequest postModel) {

        ResponseBase<CustomerResponse> response = customerService.createCustomer(postModel);

        return ResponseEntity.ok(response.getObject());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseAll> getCustomerById(@PathVariable Integer id) {

        ResponseBase<CustomerResponseAll> response = customerService.getCustomerByID(id);

        return ResponseEntity.ok(response.getObject());
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponsePartial>> getCustomer(@Valid PaginatedSearchRequest page) {
        ResponseBase<Page<CustomerResponsePartial>> response = customerService.getCustomer(page);

        return ResponseEntity.ok(response.getObject());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomerById(@RequestBody @Valid CustomerUpdateRequest body,
                                                               @PathVariable int id) {
        ResponseBase<CustomerResponse> response = customerService.updateCustomerById(body, id);

        return ResponseEntity.ok(response.getObject());
    }
}
