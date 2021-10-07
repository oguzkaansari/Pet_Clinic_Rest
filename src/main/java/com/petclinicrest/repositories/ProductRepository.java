package com.petclinicrest.repositories;

import com.petclinicrest.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByCode(String code);

    Product findByBarcode(String barcode);

    List<Product> findByCategory_Id(Integer id);
    Page<Product> findByCategory_Id(Integer id, Pageable pageable);

    List<Product> findBySupplier_Id(Integer id);
    Page<Product> findBySupplier_Id(Integer id, Pageable pageable);

    List<Product> findByUnit_Id(Integer id);
    Page<Product> findByUnit_Id(Integer id, Pageable pageable);

    List<Product> findByType_Id(Integer id);
    Page<Product> findByType_Id(Integer id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key%")
    List<Product> searchByKey(@Param("key") String key);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +

            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key%")
    Page<Product> searchByKey(@Param("key") String key, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key% AND p.supplier.id = :id")
    List<Product> searchByKeyAndSupplierId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key% AND p.supplier.id = :id")
    Page<Product> searchByKeyAndSupplierId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key% AND p.unit.id = :id")
    List<Product> searchByKeyAndUnitId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key% AND p.unit.id = :id")
    Page<Product> searchByKeyAndUnitId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key% AND p.type.id = :id")
    List<Product> searchByKeyAndTypeId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.code, ' '," +
            " p.barcode, ' ', p.name, ' ', p.unit.name, ' ', p.category.name, ' ', p.type.name, ' ', p.supplier.name) LIKE %:key% AND p.type.id = :id")
    Page<Product> searchByKeyAndTypeId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);


}
