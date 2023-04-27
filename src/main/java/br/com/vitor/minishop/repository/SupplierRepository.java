package br.com.vitor.minishop.repository;

import br.com.vitor.minishop.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Optional<Supplier> findByCNPJ(String cnpj);

    Optional<Supplier> findByEmail(String email);
}
