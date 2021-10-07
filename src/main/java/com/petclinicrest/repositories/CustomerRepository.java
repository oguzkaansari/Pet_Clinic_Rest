package com.petclinicrest.repositories;

import com.petclinicrest.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByNo(String no);

    List<Customer> findByGroup_Id(Integer id);
    Page<Customer> findByGroup_Id(Integer id, Pageable Pageable);

    List<Customer> findByStatus_Id(Integer id);
    Page<Customer> findByStatus_Id(Integer id, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE CONCAT(c.no, ' '," +
            " c.name, ' ', c.surname, ' ', c.phone1, ' ', c.phone2, ' ', c.mail, ' ', c.taxno, ' ', c.taxname) LIKE %:key%")
    List<Customer> searchByKey(@Param("key") String key);
    @Query("SELECT c FROM Customer c WHERE CONCAT(c.no, ' '," +
            " c.name, ' ', c.surname, ' ', c.phone1, ' ', c.phone2, ' ', c.mail, ' ', c.taxno, ' ', c.taxname) LIKE %:key%")
    Page<Customer> searchByKey(@Param("key") String key, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE CONCAT(c.no, ' '," +
            " c.name, ' ', c.surname, ' ', c.phone1, ' ', c.phone2, ' ', c.mail, ' ', c.taxno, ' ', c.taxname) LIKE %:key% AND c.status.id = :id")
    List<Customer> searchByKeyAndStatusId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT c FROM Customer c WHERE CONCAT(c.no, ' '," +
            " c.name, ' ', c.surname, ' ', c.phone1, ' ', c.phone2, ' ', c.mail, ' ', c.taxno, ' ', c.taxname) LIKE %:key% AND c.status.id = :id")
    Page<Customer> searchByKeyAndStatusId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);

}
