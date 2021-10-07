package com.petclinicrest.repositories;

import com.petclinicrest.entities.Customer;
import com.petclinicrest.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findByNo(String no);

    List<Ticket> findByStatus_Id(Integer id);
    Page<Ticket> findByStatus_Id(Integer id, Pageable pageable);

    List<Ticket> findBySupplier_Id(Integer id);
    Page<Ticket> findBySupplier_Id(Integer id, Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE CONCAT(t.no, ' '," +
            " t.title, ' ', t.note, ' ', t.supplier.name) LIKE %:key%")
    List<Ticket> searchByKey(@Param("key") String key);
    @Query("SELECT t FROM Ticket t WHERE CONCAT(t.no, ' '," +
            " t.title, ' ', t.note, ' ', t.supplier.name) LIKE %:key%")
    Page<Ticket> searchByKey(@Param("key") String key, Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE CONCAT(t.no, ' '," +
            " t.title, ' ', t.note, ' ', t.supplier.name) LIKE %:key% AND t.status.id = :id")
    List<Ticket> searchByKeyAndStatusId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT t FROM Ticket t WHERE CONCAT(t.no, ' '," +
            " t.title, ' ', t.note, ' ', t.supplier.name) LIKE %:key% AND t.status.id = :id")
    Page<Ticket> searchByKeyAndStatusId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE CONCAT(t.no, ' '," +
            " t.title, ' ', t.note, ' ', t.supplier.name) LIKE %:key% AND t.supplier.id = :id")
    List<Ticket> searchByKeyAndSupplierId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT t FROM Ticket t WHERE CONCAT(t.no, ' '," +
            " t.title, ' ', t.note, ' ', t.supplier.name) LIKE %:key% AND t.supplier.id = :id")
    Page<Ticket> searchByKeyAndSupplierId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);

}