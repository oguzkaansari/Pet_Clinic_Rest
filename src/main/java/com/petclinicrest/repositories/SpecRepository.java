package com.petclinicrest.repositories;

import com.petclinicrest.entities.Spec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecRepository extends JpaRepository<Spec, Integer> {
}
