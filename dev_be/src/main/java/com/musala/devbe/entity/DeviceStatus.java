package com.musala.devbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_statuses")
@Data
public class DeviceStatus {
    @Id
    private String deviceId;
    private String deviceName;
    private Boolean lampOn;
    private Boolean fanOn;
    private Boolean pirEntryDetected;
    private Boolean pirExitDetected;
    private String ipAddress;
    private String firmwareVersion;
    private LocalDateTime updatedAt;
    @Transient
    private Boolean espOnline;
}