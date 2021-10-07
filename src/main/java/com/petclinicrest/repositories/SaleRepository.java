package com.petclinicrest.repositories;

import com.petclinicrest.entities.Customer;
import com.petclinicrest.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByReceipt_Id(Integer id);
    Page<Sale> findByReceipt_Id(Integer id, Pageable pageable);

    List<Sale> findByReceipt_No(String no);
    Page<Sale> findByReceipt_No(String no, Pageable pageable);

    List<Sale> findByProduct_Id(Integer id);
    Page<Sale> findByProduct_Id(Integer id, Pageable pageable);

    List<Sale> findByProduct_Code(String code);
    Page<Sale> findByProduct_Code(String code, Pageable pageable);



    @Query("SELECT s FROM Sale s WHERE CONCAT(s.product.name, ' '," +
            " s.product.code, ' ', s.product.barcode, ' ', s.receipt.no, ' ', s.receipt.customer.name, ' ', s.receipt.customer.surname, ' ', s.product.category.name) LIKE %:key%")
    List<Sale> searchByKey(@Param("key") String key);
    @Query("SELECT s FROM Sale s WHERE CONCAT(s.product.name, ' '," +
            " s.product.code, ' ', s.product.barcode, ' ', s.receipt.no, ' ', s.receipt.customer.name, ' ', s.receipt.customer.surname, ' ', s.product.category.name) LIKE %:key%")
    Page<Sale> searchByKey(@Param("key") String key, Pageable pageable);
}
