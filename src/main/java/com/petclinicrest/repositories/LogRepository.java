package com.petclinicrest.repositories;

import com.petclinicrest.entities.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Logger, Integer> {
}
