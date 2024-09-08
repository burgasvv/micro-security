package org.example.customerservice.mapper;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.entity.Customer;
import org.example.customerservice.entity.CustomerRequest;
import org.example.customerservice.entity.CustomerResponse;
import org.example.customerservice.repository.CustomerRoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerRoleRepository customerRoleRepository;

    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .nickName(customer.getNickName())
                .role(customer.getRole().getName())
                .build();
    }

    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .password(
                        bCryptPasswordEncoder.encode(customerRequest.password())
                )
                .nickName(customerRequest.nickName())
                .role(
                        customerRoleRepository
                                .findCustomerRoleByName(customerRequest.role())
                                .orElse(null)
                )
                .build();
    }
}
