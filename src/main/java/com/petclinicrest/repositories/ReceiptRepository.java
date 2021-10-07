package com.petclinicrest.repositories;

import com.petclinicrest.entities.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    Receipt findByNo(String no);

    List<Receipt> findByCustomer_Id(Integer id);
    Page<Receipt> findByCustomer_Id(Integer id, Pageable pageable);

    List<Receipt> findByStatus_Id(Integer id);
    Page<Receipt> findByStatus_Id(Integer id, Pageable pageable);

    List<Receipt> findByPaytype_Id(Integer id);
    Page<Receipt> findByPaytype_Id(Integer id, Pageable pageable);

    List<Receipt> findByCustomer_IdAndStatus_Id(Integer id, Integer id1);
    Page<Receipt> findByCustomer_IdAndStatus_Id(Integer id, Integer id1, Pageable pageable);

    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key%")
    List<Receipt> searchByKey(@Param("key") String key);
    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key%")
    Page<Receipt> searchByKey(@Param("key") String key, Pageable pageable);

    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.customer.id =:id")
    List<Receipt> searchByKeyAndCustomerId(@Param("key") String key, Integer id);
    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.customer.id =:id")
    Page<Receipt> searchByKeyAndCustomerId(@Param("key") String key, Integer id, Pageable pageable);

    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.status.id =:id")
    List<Receipt> searchByKeyAndStatusId(@Param("key") String key, Integer id);
    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.status.id =:id")
    Page<Receipt> searchByKeyAndStatusId(@Param("key") String key, Integer id, Pageable pageable);

    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.paytype.id =:id")
    List<Receipt> searchByKeyAndPaytypeId(@Param("key") String key, Integer id);
    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.paytype.id =:id")
    Page<Receipt> searchByKeyAndPaytypeId(@Param("key") String key, Integer id, Pageable pageable);

    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.customer.id =:cid AND r.status.id =:sid")
    List<Receipt> searchByKeyAndCustomerIdAndStatusId(@Param("key") String key, Integer cid, Integer sid);
    @Query("SELECT r FROM Receipt r WHERE CONCAT(r.no, ' '," +
            " r.note, ' ', r.customer.name) LIKE %:key% AND r.customer.id =:cid AND r.status.id =:sid")
    Page<Receipt> searchByKeyAndCustomerIdAndStatusId(@Param("key") String key, Integer cid, Integer sid, Pageable pageable);


}
