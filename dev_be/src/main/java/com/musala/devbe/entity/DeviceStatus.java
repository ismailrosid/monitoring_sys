package com.musala.devbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_statuses")
@Data
public class DeviceStatus {

    @Id
    private String deviceId;

    private Boolean lampOn;
    private Boolean fanOn;
    private Boolean pirDetected;

    private Boolean espOnline;
    private String ipAddress;
    private String firmwareVersion;

    private LocalDateTime updatedAt;
}