package org.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommissionLine {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String productName;
    private Integer amount;
    private Double price;

    @ManyToOne(cascade = REFRESH)
    @JoinColumn(name = "commission_id")
    private Commission commission;
}
