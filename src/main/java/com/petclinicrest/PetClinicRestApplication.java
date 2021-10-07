package com.petclinicrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.petclinicrest.config")
public class PetClinicRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicRestApplication.class, args);
    }

}
