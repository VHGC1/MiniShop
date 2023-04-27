package br.com.vitor.minishop.service;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.domain.entity.Product;
import br.com.vitor.minishop.domain.entity.ProductImage;
import br.com.vitor.minishop.domain.entity.Supplier;
import br.com.vitor.minishop.repository.ProductImageRepository;
import br.com.vitor.minishop.repository.ProductRepository;
import br.com.vitor.minishop.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.vitor.minishop.service.UploadImageService.deleteFromBucket;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductImageRepository productImageRepository;

    public ResponseBase<ProductResponse> createProduct(ProductUpsertRequest body) {
        Optional<Supplier> optSupplier = supplierRepository.findById(body.getSupplierId());
        Supplier supplier = optSupplier
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fornecedor inválido"));

        Product product = new Product();
        product.setProductName(body.getProductName());
        product.setSupplier(supplier);
        product.setUnitPrice(body.getUnitPrice());
        product.setPackageName(body.getPackageName());
        product.setIsDiscontinued(false);

        Product productSaved = productRepository.save(product);

        List<ProductImage> productImages = setProductImagesList(product, body.getUrlList());

        product.setProductImages(productImages);
        if (productImages != null) {
            productImageRepository.saveAll(productImages);
        }

        return new ResponseBase<>(new ProductResponse(productSaved));
    }

    public ResponseBase<ProductResponse> getProductById(int id) {
        Optional<Product> optProduct = productRepository.findById(id);
        Product product = optProduct
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        return new ResponseBase<>(new ProductResponse(product));
    }

    public ResponseBase<Page<ProductResponsePartial>> getProducts(PaginatedSearchRequest query) {
        PageRequest pagination = PageRequest.of(query.getPage(), query.getSize());
        Page<Product> productsPage = productRepository.findAll(pagination);
        Page<ProductResponsePartial> productsResPage = productsPage.map(ProductResponsePartial::new);

        return new ResponseBase<>(productsResPage);
    }

    public ResponseBase<ProductResponse> updateProductById(ProductUpdateRequest body, int id) {
        Optional<Product> optProduct = productRepository.findById(id);
        Product product = optProduct
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Optional<Supplier> optSupplier = supplierRepository.findById(body.getSupplierId());
        Supplier supplier = optSupplier
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fornecedor inválido"));

        product.setProductName(body.getProductName());
        product.setSupplier(supplier);
        product.setUnitPrice(body.getUnitPrice());
        product.setPackageName(body.getPackageName());
        product.setIsDiscontinued(body.getIsDiscontinued());

        List<ProductImage> list = productImageRepository.findByProductId(id);
        List<String> newImages = body.getUrlList().stream().map(x -> x.getUrl().trim()).collect(Collectors.toList());
        List<ProductImage> productImageList = new ArrayList<>();
        List<String> urlDeletion = new ArrayList<>();

        Integer sequenceCounter = 1;
        for (int i = 0; i < list.size(); i++) {
            String aux = list.get(i).getURL();

            if (!newImages.contains(aux)) {
                Integer idRemove = list.get(i).getId();
                urlDeletion.add(list.get(i).getURL());
                productImageRepository.deleteById(idRemove);
            }
        }

        for (String urlImage : newImages) {
            Optional<ProductImage> optional = productImageRepository.findByURL(urlImage);

            if (optional.isEmpty()) {
                ProductImage productImage = new ProductImage();
                productImage.setURL(urlImage);
                productImage.setSequence(sequenceCounter);
                productImage.setProduct(product);
                productImage.setProductId(product.getId());

                productImageRepository.save(productImage);
                productImageList.add(productImage);
                sequenceCounter++;
            } else {
                ProductImage image = optional
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
                productImageList.add(image);
                sequenceCounter++;
            }
        }

        if (!urlDeletion.isEmpty())
            deleteFromBucket(urlDeletion);
        product.setProductImages(productImageList);

        Product productSaved = productRepository.save(product);

        return new ResponseBase<>(new ProductResponse(productSaved));
    }

    private List<ProductImage> setProductImagesList(Product product, List<String> urlList) {
        if (Objects.isNull(urlList) || urlList.isEmpty()) {
            return null;
        }
        ArrayList<ProductImage> productImageList = new ArrayList<>();
        ProductImage productImage;
        Integer sequenceCounter = 1;
        for (String url : urlList) {
            if (!url.trim().isEmpty()) {
                productImage = new ProductImage();
                productImage.setProduct(product);
                productImage.setSequence(sequenceCounter);
                if (urlValidate(url) && urlExtensionValidate(url)) {
                    productImage.setURL(url.trim());
                    productImageList.add(productImage);
                    sequenceCounter++;
                } else {
                    throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                            "A URL é inválida ou formato de extensão inválido!");
                }
            }
        }
        return productImageList;
    }

    public Boolean urlValidate(String url) {
        if ((url.contains("localhost") && url.contains("minishop-imagens") && url.contains("4566"))
                || (url.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean urlExtensionValidate(String url) {
        if (url.contains(".jpg") || url.contains(".png") || url.contains(".jpeg")) {
            return true;
        }
        return false;
    }
}
