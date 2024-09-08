package org.example.customerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.customerservice.entity.Customer;
import org.example.customerservice.entity.CustomerRequest;
import org.example.customerservice.entity.CustomerResponse;
import org.example.customerservice.exception.CustomerNotFoundException;
import org.example.customerservice.mapper.CustomerMapper;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    @SneakyThrows
    public CustomerResponse findById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(
                        () -> new CustomerNotFoundException(
                                "Customer with id " + customerId + " not found"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public CustomerResponse create(CustomerRequest customerRequest) {
        return customerMapper.toCustomerResponse(
                customerRepository.save(
                        customerMapper.toCustomer(customerRequest)
                )
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public CustomerResponse update(CustomerRequest customerRequest) {
        return customerMapper.toCustomerResponse(
                customerRepository.save(
                        customerMapper.toCustomer(customerRequest)
                )
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public String deleteById(Long customerId) {
        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(
                () -> new CustomerNotFoundException(
                        "Customer with id " + customerId + " not found"
                )
        );
        customerRepository.delete(customer);
        return "Customer with id " + customerId + " was successfully deleted";
    }
}
