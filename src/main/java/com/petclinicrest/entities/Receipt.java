package com.petclinicrest.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull(message = "Reçete numarası geçersiz")
    @NotEmpty(message = "Reçete numarası geçersiz")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String no;

    @DecimalMin(value = "0.0", message = "Reçete ücreti geçersiz!")
    private BigDecimal price;

    @Size(max = 500)
    private String note;

    @OneToOne
    private Customer customer;

    @OneToOne
    private PayStatus status;

    @OneToOne
    private PayType paytype;
}
