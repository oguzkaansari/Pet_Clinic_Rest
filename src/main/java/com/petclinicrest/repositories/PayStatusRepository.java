package com.petclinicrest.repositories;

import com.petclinicrest.entities.PayStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayStatusRepository extends JpaRepository<PayStatus, Integer> {
}
