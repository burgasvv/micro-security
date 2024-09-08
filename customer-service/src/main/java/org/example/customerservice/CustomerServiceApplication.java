package org.example.customerservice;

import com.github.javafaker.Faker;
import org.example.customerservice.entity.CustomerRequest;
import org.example.customerservice.entity.CustomerRole;
import org.example.customerservice.repository.CustomerRoleRepository;
import org.example.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            CustomerService customerService,
            CustomerRoleRepository customerRoleRepository
    ) {
        return _ -> {

            customerRoleRepository
                    .saveAll(
                            List.of(
                                    CustomerRole.builder().name("ADMIN").build(),
                                    CustomerRole.builder().name("USER").build()
                            )
                    );

            for (int i = 0; i < 5; i++) {

                Faker faker = new Faker();
                customerService.create(
                        CustomerRequest.builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .email(faker.internet().emailAddress())
                                .nickName(faker.name().username())
                                .password(faker.internet().password())
                                .role("USER")
                                .build()
                );
            }

        };
    }
}
