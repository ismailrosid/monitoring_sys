package com.musala.devbe.repository;

import com.musala.devbe.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findTop10ByDeviceIdOrderByTimestampDesc(String deviceId);
   Optional<SensorReading> findTopByDeviceIdOrderByTimestampDesc(String deviceId);
}
