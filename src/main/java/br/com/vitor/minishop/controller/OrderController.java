package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.domain.dto.OrderResponse;
import br.com.vitor.minishop.domain.dto.OrderResponseWithItens;
import br.com.vitor.minishop.domain.dto.PaginatedSearchRequest;
import br.com.vitor.minishop.domain.dto.ResponseBase;
import br.com.vitor.minishop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
@RolesAllowed({"ROLE_BACKOFFICE_USER", "ROLE_ADMIN_USER"})
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> search(@Valid PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<OrderResponse>> searchResponse = orderService.search(searchRequest);

        return ResponseEntity.ok(searchResponse.getObject());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseWithItens> searchById(@PathVariable Integer id) {

        ResponseBase<OrderResponseWithItens> searchByIdResponse = orderService.searchById(id);

        return ResponseEntity.ok(searchByIdResponse.getObject());
    }
}
