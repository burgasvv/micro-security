package org.example.orderservice.repository;

import org.example.orderservice.entity.CommissionLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionLineRepository extends JpaRepository<CommissionLine, Long> {
}
