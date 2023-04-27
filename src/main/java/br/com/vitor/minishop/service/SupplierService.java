package br.com.vitor.minishop.service;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.domain.entity.Supplier;
import br.com.vitor.minishop.repository.SupplierRepository;
import br.com.vitor.minishop.util.ValidateCNPJ;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidateCNPJ validateCNPJ;

    public ResponseBase<SupplierResponse> createSupplier(SupplierCreateRequest postModel) {

        isValidCNPJ(postModel.getCNPJ());
        isValidEmail(postModel.getEmail());
        isValidUF(postModel.getUF());

        // Create an entity based on the DTO
        Supplier modelDb = new Supplier();
        modelDb.setCompanyName(postModel.getCompanyName());
        modelDb.setCNPJ(postModel.getCNPJ());
        modelDb.setEmail(postModel.getEmail());
        modelDb.setCity(postModel.getCity());
        modelDb.setUF(UF.valueOf(postModel.getUF()));
        modelDb.setContactName(postModel.getContactName());
        modelDb.setPhone(postModel.getPhone());

        // Use the repository to save
        Supplier supplierSaved = supplierRepository.save(modelDb);

        // Mapeia de entidade para dto
        SupplierResponse supplierResponse = new SupplierResponse(supplierSaved);

        return new ResponseBase<>(supplierResponse);
    }

    public ResponseBase<Page<SupplierResponsePartial>> search(PaginatedSearchRequest searchRequest) {
        // a Pagina atual não pode ser menor que 0
        if (searchRequest.getPage() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The current page index must start at 0");

        // a quantidade de itens por pagina deve ser entre 1 e 50
        if (searchRequest.getSize() < 1 || searchRequest.getSize() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The items' quantity per page must be between 1 and 50");

        // cria um objeto de consulta paginada(PageRequest) a partir dos parametros informados
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        // pesquisa no repositorio usando a consulta paginada
        Page<Supplier> supplierPage = supplierRepository.findAll(pageRequest);

        // Mapeia da entidade(Customer) para o DTO(CustomerResponse)
        Page<SupplierResponsePartial> supplierResponsePage = supplierPage.map(SupplierResponsePartial::new);
        return new ResponseBase<>(supplierResponsePage);
    }

    public ResponseBase<List<SupplierResponseShort>> searchAll() {
        List<Supplier> supplierList = supplierRepository.findAll();

        if (supplierList.isEmpty())
            throw new RuntimeException("Nothing to list");

        List<SupplierResponseShort> supplierListResponse = supplierList.stream()
                .map(SupplierResponseShort::new)
                .collect(Collectors.toList());

        return new ResponseBase<>(supplierListResponse);
    }

    public ResponseBase<SupplierResponseAll> searchById(Integer id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);

        Supplier supplier = supplierOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto Não encontrado"));

        List<ProductResponseWithoutSupplier> listOfProducts = getListOfProductResponse(supplier);

        SupplierResponseAll supplierResponseAll = new SupplierResponseAll(supplier, listOfProducts);

        return new ResponseBase<>(supplierResponseAll);
    }

    public ResponseBase<SupplierResponse> updateSupplier(Integer id, SupplierUpdateRequest supplierUpdateRequest) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);

        Supplier supplier = supplierOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto Não encontrado"));

        if (!supplier.getEmail().equals(supplierUpdateRequest.getEmail()))
            isValidEmail(supplierUpdateRequest.getEmail());
        if (!supplier.getUF().toString().equals(supplierUpdateRequest.getUF()))
            isValidUF(supplierUpdateRequest.getUF());

        supplier.setEmail(supplierUpdateRequest.getEmail());
        supplier.setUF(UF.valueOf(supplierUpdateRequest.getUF()));
        supplier.setCompanyName(supplierUpdateRequest.getCompanyName());
        supplier.setCity(supplierUpdateRequest.getCity());
        supplier.setContactName(supplierUpdateRequest.getContactName());
        supplier.setPhone(supplierUpdateRequest.getPhone());

        Supplier supplierSaved = supplierRepository.save(supplier);

        SupplierResponse supplierResponse = new SupplierResponse(supplierSaved);

        return new ResponseBase<>(supplierResponse);
    }

    private void isValidUF(String state) {
        if (!UF.contains(state))
            throw new IllegalArgumentException("UF não encontrada!");
    }

    private void isValidEmail(String email) {
        Optional<Supplier> supplierEmailOptional = supplierRepository.findByEmail(email);
        if (supplierEmailOptional.isPresent())
            throw new IllegalArgumentException("Email já cadastrado.");
    }

    private void isValidCNPJ(String CNPJ) {
        if (!validateCNPJ.isCNPJ(CNPJ))
            throw new IllegalArgumentException("CNPJ invalido.");
        // Checking to see if CNPJ is unique
        Optional<Supplier> supplierCNPJOptional = supplierRepository.findByCNPJ(CNPJ);
        if (supplierCNPJOptional.isPresent())
            throw new IllegalArgumentException("CNPJ já cadastrado.");
    }

    private List<ProductResponseWithoutSupplier> getListOfProductResponse(Supplier supplier) {
        List<ProductResponseWithoutSupplier> listOfProducts = new ArrayList<>();

        if (!supplier.getProducts().isEmpty()) {
            listOfProducts = supplier.getProducts().stream()
                    .map(ProductResponseWithoutSupplier::new)
                    .collect(Collectors.toList());
        }
        return listOfProducts;
    }
}
