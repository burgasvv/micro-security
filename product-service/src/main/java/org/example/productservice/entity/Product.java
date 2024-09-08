package org.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Check(constraints = "price > 0")
    private Double price;

    @Check(constraints = "amount > 0")
    private Integer amount;

    @ManyToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;
}
