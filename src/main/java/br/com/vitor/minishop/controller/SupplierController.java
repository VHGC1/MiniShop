package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
@RequiredArgsConstructor
@RolesAllowed({"ROLE_BACKOFFICE_USER", "ROLE_ADMIN_USER"})
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity search(@Valid PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<SupplierResponsePartial>> searchResponse = supplierService.search(searchRequest);

        return ResponseEntity.ok(searchResponse.getObject());
    }

    @GetMapping(value = "/all")
    public ResponseEntity searchAll() {

        ResponseBase<List<SupplierResponseShort>> searchAllResponse = supplierService.searchAll();

        return ResponseEntity.ok(searchAllResponse.getObject());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity searchById(@PathVariable Integer id) {

        ResponseBase<SupplierResponseAll> searchByIdResponse = supplierService.searchById(id);

        return ResponseEntity.ok(searchByIdResponse.getObject());
    }

    @PostMapping
    public ResponseEntity registerSupplier(@Valid @RequestBody SupplierCreateRequest postModel) {

        ResponseBase<SupplierResponse> retorno = supplierService.createSupplier(postModel);

        return ResponseEntity.ok(retorno.getObject());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity registerSupplier(
            @PathVariable Integer id,
            @RequestBody @Valid SupplierUpdateRequest supplierUpdateRequest
    ) {

        ResponseBase<SupplierResponse> retorno = supplierService.updateSupplier(id, supplierUpdateRequest);

        return ResponseEntity.ok(retorno.getObject());
    }
}
