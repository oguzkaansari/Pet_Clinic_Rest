package com.petclinicrest.repositories;

import com.petclinicrest.entities.ReferenceHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceHolderRepository extends JpaRepository<ReferenceHolder, Integer> {
    Optional<ReferenceHolder> findByUid(Integer uid);
}
