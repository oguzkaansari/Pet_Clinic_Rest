package com.petclinicrest.repositories;

import com.petclinicrest.entities.TestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTypeRepository extends JpaRepository<TestType, Integer> {
}
