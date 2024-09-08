package org.example.customerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRole {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "role",
            fetch = EAGER,
            cascade = {PERSIST, MERGE}
    )
    private List<Customer> customers = new ArrayList<>();
}
