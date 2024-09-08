package org.example.customerservice.repository;

import org.example.customerservice.entity.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRoleRepository extends JpaRepository<CustomerRole, Long> {

    Optional<CustomerRole> findCustomerRoleByName(String name);
}
