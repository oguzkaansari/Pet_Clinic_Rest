package com.petclinicrest.repositories;

import com.petclinicrest.entities.PayType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayTypeRepository extends JpaRepository<PayType, Integer> {
}
