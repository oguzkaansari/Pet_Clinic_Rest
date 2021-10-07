package com.petclinicrest.repositories;

import com.petclinicrest.entities.CustomerGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Integer> {

    @Query("SELECT c FROM CustomerGroup c WHERE CONCAT(c.name, ' ', c.detail) LIKE %:key%")
    List<CustomerGroup> searchByKey(@Param("key") String key);
    @Query("SELECT c FROM CustomerGroup c WHERE CONCAT(c.name, ' ', c.detail) LIKE %:key%")
    Page<CustomerGroup> searchByKey(@Param("key") String key, Pageable pageable);

}
