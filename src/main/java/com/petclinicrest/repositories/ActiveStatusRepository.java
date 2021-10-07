package com.petclinicrest.repositories;

import com.petclinicrest.entities.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveStatusRepository extends JpaRepository<ActiveStatus, Integer> {
}
