package org.example.productservice;

import com.github.javafaker.Faker;
import org.example.productservice.entity.Category;
import org.example.productservice.entity.Product;
import org.example.productservice.repository.CategoryRepository;
import org.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        return _ -> {

            categoryRepository.saveAll(
                    List.of(
                            Category.builder()
                                    .name("Milk Products")
                                    .description("New Milk Products creative description")
                                    .build(),
                            Category.builder()
                                    .name("Vegetables")
                                    .description("New Vegetables creative description")
                                    .build(),
                            Category.builder()
                                    .name("Fruits")
                                    .description("New Fruits creative description")
                                    .build()
                    )
            );

            for (int i = 0; i < 15; i++) {
                Faker faker = new Faker();
                productRepository.save(
                        Product.builder()
                                .name(faker.food().ingredient())
                                .description(faker.lorem().paragraph(5))
                                .category(
                                        categoryRepository.findById(
                                                (long) faker.number().numberBetween(1,4)
                                        ).orElse(null)
                                )
                                .amount(faker.number().numberBetween(500,1500))
                                .price(faker.number().randomDouble(2, 50, 350))
                                .build()
                );
            }
        };
    }
}
