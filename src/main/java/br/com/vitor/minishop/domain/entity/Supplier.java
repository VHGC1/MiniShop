package br.com.vitor.minishop.domain.entity;

import br.com.vitor.minishop.domain.dto.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "Supplier")
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CompanyName", length = 100, nullable = false)
    private String companyName;

    @Column(name = "CNPJ", length = 14, nullable = false, unique = true)
    private String CNPJ;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "City", length = 100, nullable = false)
    private String city;

    @Column(name = "UF", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private UF UF;

    @Column(name = "ContactName", length = 50)
    private String contactName;

    @Column(name = "Phone", length = 15)
    private String phone;

    @OneToMany(mappedBy = "supplierId", fetch = FetchType.LAZY)
    private List<Product> products;
}
