package br.com.vitor.minishop.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;

@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Setter(AccessLevel.NONE)
    @Column(name = "OrderId", nullable = false, insertable = false, updatable = false)
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    private CustomerOrder order;

    @Setter(AccessLevel.NONE)
    @Column(name = "ProductId", nullable = false, insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    private Product product;
}
