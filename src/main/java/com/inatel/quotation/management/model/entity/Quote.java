package com.inatel.quotation.management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "quote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "stock_id")
    private String stockId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
