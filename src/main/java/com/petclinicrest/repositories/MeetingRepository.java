package com.petclinicrest.repositories;

import com.petclinicrest.entities.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    List<Meeting> findByDate(Date date);
    Page<Meeting> findByDate(Date date, Pageable pageable);

    List<Meeting> findByCustomer_Id(Integer id);
    Page<Meeting> findByCustomer_Id(Integer id, Pageable pageable);

    List<Meeting> findByCustomer_No(String no);
    Page<Meeting> findByCustomer_No(String no, Pageable pageable);

    List<Meeting> findByPet_Id(Integer id);
    Page<Meeting> findByPet_Id(Integer id, Pageable pageable);

    List<Meeting> findByPet_Chipno(String chipno);
    Page<Meeting> findByPet_Chipno(String chipno, Pageable pageable);

    List<Meeting> findByPet_Cardno(String cardno);
    Page<Meeting> findByPet_Cardno(String cardno, Pageable pageable);

    List<Meeting> findByDoctor_Id(Integer id);
    Page<Meeting> findByDoctor_Id(Integer id, Pageable pageable);

    List<Meeting> findByDoctor_IdAndDate(Integer id, Date date);
    Page<Meeting> findByDoctor_IdAndDate(Integer id, Date date, Pageable pageable);

    List<Meeting> findByDoctor_IdAndCustomer_Id(Integer id, Integer id1);
    Page<Meeting> findByDoctor_IdAndCustomer_Id(Integer id, Integer id1, Pageable pageable);

    List<Meeting> findByDoctor_IdAndCustomer_No(Integer id, String no);
    Page<Meeting> findByDoctor_IdAndCustomer_No(Integer id, String no, Pageable pageable);

    List<Meeting> findByDoctor_IdAndPet_Id(Integer id, Integer id1);
    Page<Meeting> findByDoctor_IdAndPet_Id(Integer id, Integer id1, Pageable pageable);

    List<Meeting> findByDoctor_IdAndPet_Chipno(Integer id, String chipno);
    Page<Meeting> findByDoctor_IdAndPet_Chipno(Integer id, String chipno, Pageable pageable);

    List<Meeting> findByDoctor_IdAndPet_Cardno(Integer id, String cardno);
    Page<Meeting> findByDoctor_IdAndPet_Cardno(Integer id, String cardno, Pageable pageable);

    List<Meeting> findByDateLessThanEqualAndHourLessThan(Date date, LocalTime hour);
    Page<Meeting> findByDateLessThanEqualAndHourLessThan(Date date, LocalTime hour, Pageable pageable);

    List<Meeting> findByDateGreaterThanEqualAndHourGreaterThan(Date date, LocalTime hour);
    Page<Meeting> findByDateGreaterThanEqualAndHourGreaterThan(Date date, LocalTime hour, Pageable pageable);

    List<Meeting> findByDateLessThanEqualAndHourLessThanAndDoctor_Id(Date date, LocalTime hour, Integer id);
    Page<Meeting> findByDateLessThanEqualAndHourLessThanAndDoctor_Id(Date date, LocalTime hour, Integer id, Pageable pageable);

    List<Meeting> findByDateGreaterThanEqualAndHourGreaterThanAndDoctor_Id(Date date, LocalTime hour, Integer id);
    Page<Meeting> findByDateGreaterThanEqualAndHourGreaterThanAndDoctor_Id(Date date, LocalTime hour, Integer id, Pageable pageable);



}
