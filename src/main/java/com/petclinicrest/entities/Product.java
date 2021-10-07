package com.petclinicrest.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotNull(message = "Ürün Adı geçersiz")
    @NotEmpty(message = "Ürün Adı geçersiz")
    private String name;

    @OneToOne
    private Unit unit;

    @OneToOne
    private Category category;

    @OneToOne
    private ProductType type;

    @OneToOne
    private Supplier supplier;

    @OneToOne
    private Tax tax;

    @Column(unique = true)
    @NotNull(message = "Ürün barkodu geçersiz")
    @NotEmpty(message = "Ürün barkodu geçersiz")
    @Pattern(regexp="(^$|[0-9]{13})")
    private String barcode;

    @Column(unique = true)
    @NotNull(message = "Ürün kodu Geçersiz")
    @NotEmpty(message = "Ürün kodu Geçersiz")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String code;

    @DecimalMin(value = "0.0", message = "Alış fiyatı geçersiz!")
    private BigDecimal buy_price;

    @DecimalMin(value = "0.0", message = "Satış fiyatı geçersiz!")
    private BigDecimal sell_price;

    @DecimalMin(value = "0.0", message = "Stok miktarı geçersiz!")
    private BigDecimal stock;

    @DecimalMin(value = "0.0", message = "Kritik stok miktarı geçersiz!")
    private BigDecimal crit_stock;

}
