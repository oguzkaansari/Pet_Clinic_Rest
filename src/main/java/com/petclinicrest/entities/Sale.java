package com.petclinicrest.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Product product;

    @OneToOne
    private Receipt receipt;

    @DecimalMin(value = "0.0", message = "Miktar geçersiz!")
    private BigDecimal amount;

    @Size(max = 500)
    private String note;

}
