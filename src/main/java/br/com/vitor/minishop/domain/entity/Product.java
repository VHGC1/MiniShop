package br.com.vitor.minishop.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ProductName", length = 100, nullable = false)
    private String productName;

    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @Column(name = "IsDiscontinued", nullable = false)
    private Boolean isDiscontinued = false;

    @Column(name = "PackageName", length = 100)
    private String packageName;

    @Setter(AccessLevel.NONE)
    @Column(name = "SupplierId", nullable = false, insertable = false, updatable = false)
    private Integer supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupplierId")
    private Supplier supplier;

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private List<ProductImage> productImages;
}