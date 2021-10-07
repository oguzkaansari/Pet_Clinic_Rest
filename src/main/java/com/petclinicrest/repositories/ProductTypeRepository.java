package com.petclinicrest.repositories;

import com.petclinicrest.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}
