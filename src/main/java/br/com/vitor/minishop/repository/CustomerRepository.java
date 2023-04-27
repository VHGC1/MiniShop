package br.com.vitor.minishop.repository;

import br.com.vitor.minishop.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCPF(String cpf);
    Optional<Customer> findByEmail(String cpf);

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(TotalAmount) FROM CustomerOrder WHERE CustomerId = :id"
    )
    Optional<Double> totalSpentById(@Param("id") int id);
}
