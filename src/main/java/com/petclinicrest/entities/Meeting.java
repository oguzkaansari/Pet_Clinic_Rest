package com.petclinicrest.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User doctor;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Pet pet;

    @NotNull(message = "Randevu tarihi boş olamaz")
    private Date date;

    @NotNull(message = "Saat geçersiz")
    private LocalTime hour;
}
