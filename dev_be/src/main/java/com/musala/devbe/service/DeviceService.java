package com.musala.devbe.service;

import com.musala.devbe.entity.DeviceLog;
import com.musala.devbe.entity.DeviceStatus;
import com.musala.devbe.entity.ElectricityTokenHistory;
import com.musala.devbe.entity.SensorReading;
import com.musala.devbe.repository.DeviceLogRepository;
import com.musala.devbe.repository.DeviceStatusRepository;
import com.musala.devbe.repository.ElectricityTokenHistoryRepository;
import com.musala.devbe.repository.SensorReadingRepository;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class DeviceService {

    private final DeviceStatusRepository statusRepo;
    private final DeviceLogRepository logRepo;
    private final SensorReadingRepository sensorRepo;
    private final ElectricityTokenHistoryRepository tokenRepo;

    public DeviceService(DeviceStatusRepository statusRepo,
                         DeviceLogRepository logRepo,
                         SensorReadingRepository sensorRepo,
                         ElectricityTokenHistoryRepository tokenRepo
                         ) {
        this.statusRepo = statusRepo;
        this.logRepo    = logRepo;
        this.sensorRepo = sensorRepo;
        this.tokenRepo = tokenRepo;
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
     * Save sensor reading from microcontroller with current timestamp
     *
     * @param sensor the sensor reading
     * @return the saved sensor reading
     */
    public SensorReading saveSensor(SensorReading sensor) {
        sensor.setTimestamp(LocalDateTime.now());
        SensorReading saved = sensorRepo.save(sensor);
        return saved;
        
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


    /**
     * Get device information by deviceId
     *
     * @param deviceId the device ID
     * @return device information
     */
    public DeviceStatus getDeviceInfo(String deviceId) {
        DeviceStatus device = statusRepo
                .findById(deviceId)
                .orElse(null);

        if (device == null) {
            return null;
        }
        
        if (device.getUpdatedAt() != null) {
            boolean online = device.getUpdatedAt()
                    .isAfter(LocalDateTime.now().minusMinutes(1));
            device.setEspOnline(online);
              if (!online) {
                device.setLampOn(false);
                device.setFanOn(false);
                device.setPirEntryDetected(false);
                device.setPirExitDetected(false);
                }
        }
        return device;
    }

    /**
     * Create new device
     *
     * @param device the device information
     * @return created device
     */
    public DeviceStatus addDevice(DeviceStatus device) {

        device.setUpdatedAt(LocalDateTime.now());

        return statusRepo.save(device);
    }


    public ElectricityTokenHistory addToken(
        ElectricityTokenHistory token
    ) {

        double tariff = 1500.0;
        token.setTariffPerKwh(tariff);
        token.setPurchasedKwh(
                token.getNominalRupiah() / tariff
        );
        token.setCreatedAt(LocalDateTime.now());

        return tokenRepo.save(token);
    }

    public Map<String, Object> getTokenSummary(
            String deviceId
    ) {

        List<ElectricityTokenHistory> histories =
                tokenRepo.findCurrentMonthByDeviceId(deviceId);
        double nominal = histories.stream()
                .mapToDouble(ElectricityTokenHistory::getNominalRupiah)
                .sum();
        double totalKwh = histories.stream()
                .mapToDouble(ElectricityTokenHistory::getPurchasedKwh)
                .sum();
        double usedKwh = 0.0;
                LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter periodFormatter =
                DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));

        DateTimeFormatter updatedFormatter =
                DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm",new Locale("id", "ID"));

        Map<String, Object> result = new HashMap<>();

        result.put("nominalRupiah", nominal);
        result.put("totalKwh", totalKwh);
        result.put("usedKwh", usedKwh);
        result.put("remainingKwh", totalKwh - usedKwh);

        result.put("period",now.format(periodFormatter));
        result.put("updatedAt",now.format(updatedFormatter));

        return result;
    }
}