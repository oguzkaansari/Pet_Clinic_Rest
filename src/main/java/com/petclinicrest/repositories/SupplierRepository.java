package com.petclinicrest.repositories;

import com.petclinicrest.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT s FROM Supplier s WHERE CONCAT(s.name, ' ', s.mail, ' ', s.phone) LIKE %:key%")
    List<Supplier> searchByKey(@Param("key") String key);
    @Query("SELECT s FROM Supplier s WHERE CONCAT(s.name, ' ', s.mail, ' ', s.phone) LIKE %:key%")
    Page<Supplier> searchByKey(@Param("key") String key, Pageable pageable);
}
