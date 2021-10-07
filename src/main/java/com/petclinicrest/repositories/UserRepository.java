package com.petclinicrest.repositories;

import com.petclinicrest.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailEqualsAllIgnoreCase(String email);

    List<User> findByRoles_Id(Integer id);
    Page<User> findByRoles_Id(Integer id, Pageable pageable);

    List<User> findByStatus_Id(Integer id);
    Page<User> findByStatus_Id(Integer id, Pageable pageable);

    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ' ', u.phone, ' ', u.email) LIKE %:key%")
    List<User> searchByKey(@Param("key") String key);
    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ' ', u.phone, ' ', u.email) LIKE %:key%")
    Page<User> searchByKey(@Param("key") String key, Pageable pageable);

    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ' ', u.phone, ' ', u.email) LIKE %:key% AND u.status.id = :id")
    List<User> searchByKeyAndStatusId(@Param("key") String key, Integer id);
    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ' ', u.phone, ' ', u.email) LIKE %:key% AND u.status.id = :id")
    Page<User> searchByKeyAndStatusId(@Param("key") String key, Integer id, Pageable pageable);
}
