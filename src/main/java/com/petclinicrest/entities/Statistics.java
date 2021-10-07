package com.petclinicrest.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long petVaccineCount;
    private Long farmVaccineCount;
    private Long otherProductsCount;

    private BigDecimal totalMoney;
    private BigDecimal totalPayIn;
    private BigDecimal totalPayOut;

    private Long totalCustomers;
    private Long totalReceipts;
    private Long totalTickets;
}
