ackage com.musala.devbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "electricity_tokens")
@Data
public class ElectricityToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    private Double nominalRupiah;

    private Double tariffPerKwh;

    private Double totalKwh;

    private Double usedKwh;

    private Double remainingKwh;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}