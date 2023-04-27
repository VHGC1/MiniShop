package br.com.vitor.minishop.domain.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "ProductImage")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "URL", length = 2080, nullable = false)
    private String URL;

    @Column(name = "Sequence", nullable = false)
    private Integer Sequence;

    @Column(name = "ProductId", nullable = false, insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    private Product product;
}
