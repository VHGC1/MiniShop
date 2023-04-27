package br.com.vitor.minishop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FirstName", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 100, nullable = false)
    private String lastName;

    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String CPF;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY)
    private List<CustomerOrder> orders;
}
