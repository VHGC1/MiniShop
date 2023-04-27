package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@RolesAllowed({"ROLE_BACKOFFICE_USER", "ROLE_ADMIN_USER"})
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductUpsertRequest body) {
        ResponseBase<ProductResponse> response = productService.createProduct(body);

        return ResponseEntity.ok(response.getObject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        ResponseBase<ProductResponse> response = productService.getProductById(id);

        return ResponseEntity.ok(response.getObject());
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponsePartial>> getProducts(@Valid PaginatedSearchRequest query) {
        ResponseBase<Page<ProductResponsePartial>> response = productService.getProducts(query);

        return ResponseEntity.ok(response.getObject());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@RequestBody @Valid ProductUpdateRequest body,
                                                             @PathVariable int id) {
        ResponseBase<ProductResponse> response = productService.updateProductById(body, id);

        return ResponseEntity.ok(response.getObject());
    }
}
