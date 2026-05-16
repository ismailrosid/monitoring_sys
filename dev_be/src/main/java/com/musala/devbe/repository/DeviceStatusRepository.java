package com.musala.devbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.devbe.entity.DeviceStatus;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, String> {
}