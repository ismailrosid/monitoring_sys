package com.musala.devbe.service;

import com.musala.devbe.entity.DeviceLog;
import com.musala.devbe.entity.DeviceStatus;
import com.musala.devbe.entity.SensorReading;
import com.musala.devbe.repository.DeviceLogRepository;
import com.musala.devbe.repository.DeviceStatusRepository;
import com.musala.devbe.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceService {

    private final DeviceStatusRepository statusRepo;
    private final DeviceLogRepository logRepo;
    private final SensorReadingRepository sensorRepo;

    public DeviceService(DeviceStatusRepository statusRepo,
                         DeviceLogRepository logRepo,
                         SensorReadingRepository sensorRepo) {
        this.statusRepo = statusRepo;
        this.logRepo    = logRepo;
        this.sensorRepo = sensorRepo;
    }

    /**
     * Save or update device status — upsert by deviceId
     *
     * @param status the device status
     * @return the updated device status
     */
    public DeviceStatus updateStatus(DeviceStatus status) {
        status.setUpdatedAt(LocalDateTime.now());
        return statusRepo.save(status);
    }

    /**
     * Get latest device status by deviceId
     *
     * @param deviceId the device ID
     * @return the device status
     */
    public DeviceStatus getStatus(String deviceId) {
        return statusRepo.findById(deviceId).orElse(null);
    }

    /**
     * Save relay on/off log with current timestamp
     *
     * @param log the device log
     * @return the saved device log
     */
    public DeviceLog saveLog(DeviceLog log) {
        log.setTimestamp(LocalDateTime.now());
        return logRepo.save(log);
    }

    /**
     * Get all device logs by deviceId
     *
     * @param deviceId the device ID
     * @return list of device logs
     */

    public List<DeviceLog> getLogs(String deviceId) {
        return logRepo.findByDeviceIdOrderByTimestampDesc(deviceId);
    }

    /**
     * Save sensor reading from ESP32 with current timestamp
     *
     * @param sensor the sensor reading
     * @return the saved sensor reading
     */
    public SensorReading saveSensor(SensorReading sensor) {
        sensor.setTimestamp(LocalDateTime.now());
        return sensorRepo.save(sensor);
    }

    /**
     * Get the most recent sensor reading for a device
     *
     * @param deviceId the device ID
     * @return the latest sensor reading
     */
    public SensorReading getLatestSensor(String deviceId) {
        return sensorRepo.findTop10ByDeviceIdOrderByTimestampDesc(deviceId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Get latest sensor readings for chart
     *
     * @param deviceId the device ID
     * @return list of sensor readings
     */
    public List<SensorReading> getChartData(String deviceId) {
        return sensorRepo.findTop10ByDeviceIdOrderByTimestampDesc(deviceId);
    }
}