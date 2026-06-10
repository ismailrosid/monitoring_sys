package com.musala.devbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "electricity_token_histories")
@Data
public class ElectricityTokenHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String deviceId;
    private Double nominalRupiah;
    private Double tariffPerKwh;
    private Double purchasedKwh;
    private String note;
    private LocalDateTime createdAt;
}