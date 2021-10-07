package com.petclinicrest.repositories;

import com.petclinicrest.entities.Customer;
import com.petclinicrest.entities.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {

    Test findByNo(String no);

    List<Test> findByStatus_Id(Integer id);
    Page<Test> findByStatus_Id(Integer id, Pageable pageable);

    List<Test> findByPet_Id(Integer id);
    Page<Test> findByPet_Id(Integer id, Pageable pageable);

    List<Test> findByPet_Chipno(String chipno);
    Page<Test> findByPet_Chipno(String chipno, Pageable pageable);

    List<Test> findByPet_Cardno(String cardno);
    Page<Test> findByPet_Cardno(String cardno, Pageable pageable);

    @Query("SELECT t FROM Test t WHERE CONCAT(t.no, ' '," +
            " t.type.name, ' ', t.pet.name, ' ', t.pet.chipno, ' ', t.pet.cardno, ' ', t.pet.race.name, ' ', t.pet.spec.name, ' ', t.pet.color, ' ', t.pet.gender.name) LIKE %:key%")
    List<Test> searchByKey(@Param("key") String key);
    @Query("SELECT t FROM Test t WHERE CONCAT(t.no, ' '," +
            " t.type.name, ' ', t.pet.name, ' ', t.pet.chipno, ' ', t.pet.cardno, ' ', t.pet.race.name, ' ', t.pet.spec.name, ' ', t.pet.color, ' ', t.pet.gender.name) LIKE %:key%")
    Page<Test> searchByKey(@Param("key") String key, Pageable pageable);

    @Query("SELECT t FROM Test t WHERE CONCAT(t.no, ' '," +
            " t.type.name, ' ', t.pet.name, ' ', t.pet.chipno, ' ', t.pet.cardno, ' ', t.pet.race.name, ' ', t.pet.spec.name, ' ', t.pet.color, ' ', t.pet.gender.name) LIKE %:key% AND t.status.id = :id")
    List<Test> searchByKeyAndStatusId(@Param("key") String key, @Param("id") Integer id);
    @Query("SELECT t FROM Test t WHERE CONCAT(t.no, ' '," +
            " t.type.name, ' ', t.pet.name, ' ', t.pet.chipno, ' ', t.pet.cardno, ' ', t.pet.race.name, ' ', t.pet.spec.name, ' ', t.pet.color, ' ', t.pet.gender.name) LIKE %:key% AND t.status.id = :id")
    Page<Test> searchByKeyAndStatusId(@Param("key") String key, @Param("id") Integer id, Pageable pageable);

}
