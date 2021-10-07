package com.petclinicrest.repositories;

import com.petclinicrest.entities.TestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestStatusRepository extends JpaRepository<TestStatus, Integer> {
}
