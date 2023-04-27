package br.com.vitor.minishop.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "CustomerOrder")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "OrderDate", nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(name = "TotalAmount", nullable = false)
    private double totalAmount;

    @Setter(AccessLevel.NONE)
    @Column(name = "CustomerId", nullable = false, insertable = false, updatable = false)
    private Integer customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
