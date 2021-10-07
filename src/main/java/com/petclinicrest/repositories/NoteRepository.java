package com.petclinicrest.repositories;

import com.petclinicrest.entities.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByUser_Id(Integer id);
    Page<Note> findByUser_Id(Integer id, Pageable pageable);

    List<Note> findByUser_IdAndDate(Integer id, Date date);
    Page<Note> findByUser_IdAndDate(Integer id, Date date, Pageable pageable);

    @Query("SELECT n FROM Note n WHERE CONCAT(n.title, ' ', n.note) LIKE %:key% AND n.user.id =:id")
    List<Note> searchByKey(@Param("key") String key, Integer id);
    @Query("SELECT n FROM Note n WHERE CONCAT(n.title, ' ', n.note) LIKE %:key% AND n.user.id =:id")
    Page<Note> searchByKey(@Param("key") String key, Integer id, Pageable pageable);

}
