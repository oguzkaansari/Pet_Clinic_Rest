package com.petclinicrest.repositories;

import com.petclinicrest.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:key%")
    List<Category> searchByKey(@Param("key") String key);
    @Query("SELECT c FROM Category c WHERE c.name LIKE %:key%")
    Page<Category> searchByKey(@Param("key") String key, Pageable pageable);

}
