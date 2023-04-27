package br.com.vitor.minishop.repository;

import br.com.vitor.minishop.domain.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProductId(int id);
    Optional<ProductImage> findByURL(String url);

    Optional<ProductImage> findByURLAndProductId(String urlImage, int id);
}
