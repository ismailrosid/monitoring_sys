package com.musala.devbe.repository;

import com.musala.devbe.entity.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeviceLogRepository extends JpaRepository<DeviceLog, Long> {
    List<DeviceLog> findByDeviceIdOrderByTimestampDesc(String deviceId);
}