package com.musala.devbe.controller;

import com.musala.devbe.entity.DeviceLog;
import com.musala.devbe.entity.DeviceStatus;
import com.musala.devbe.entity.SensorReading;
import com.musala.devbe.service.DeviceService;
import com.musala.devbe.entity.ElectricityToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    /**
     * GET latest device status — called by Vue dashboard
     *
     * @param deviceId the device ID
     * @return the device status
     */
    @GetMapping("/status/{deviceId}")
    public DeviceStatus getStatus(@PathVariable String deviceId) {
        log.info("Fetching status for deviceId: {}", deviceId);
        try {
            DeviceStatus status = service.getStatus(deviceId);
            if (status == null) {
                log.warn("No status found for deviceId: {}", deviceId);
            } else {
                log.info("SUCCESS fetching status for deviceId: {}", deviceId);
            }
            return status;
        } catch (Exception e) {
            log.error("FAILED fetching status for deviceId: {}", deviceId, e);
            throw e;
        }
    }

    /**
     * UPDATE device status — called by ESP32 or Vue control page
     *
     * @param status the device status
     * @return the updated device status
     */
    @PostMapping("/status")
    public DeviceStatus updateStatus(@RequestBody DeviceStatus status) {

        log.info("Updating status for deviceId: {}", status.getDeviceId());

        try {

            DeviceStatus updated = service.updateStatus(status);

            log.info(
                    "SUCCESS updating status | deviceId={} | lampOn={} | fanOn={}",
                    updated.getDeviceId(),
                    updated.getLampOn(),
                    updated.getFanOn()
            );

            return updated;

        } catch (Exception e) {

            log.error(
                    "FAILED updating status for deviceId: {}",
                    status.getDeviceId(),
                    e
            );

            throw e;
        }
    }

    /**
     * GET device logs
     *
     * @param deviceId the device ID
     * @return list of device logs
     */
    @GetMapping("/log/{deviceId}")
    public List<DeviceLog> getLogs(@PathVariable String deviceId) {

        log.info("Fetching logs for deviceId: {}", deviceId);

        try {

            List<DeviceLog> logs = service.getLogs(deviceId);

            log.info(
                    "SUCCESS fetching logs | deviceId={} | totalLogs={}",
                    deviceId,
                    logs.size()
            );

            return logs;

        } catch (Exception e) {

            log.error(
                    "FAILED fetching logs for deviceId: {}",
                    deviceId,
                    e
            );

            throw e;
        }
    }

    /**
     * INSERT device log — called when relay state changes
     *
     * @param logEntry the device log
     * @return the saved device log
     */
    @PostMapping("/log")
    public DeviceLog addLog(@RequestBody DeviceLog logEntry) {

        log.info(
                "Adding log for deviceId: {} - action: {}",
                logEntry.getDeviceId(),
                logEntry.getAction()
        );

        try {

            DeviceLog saved = service.saveLog(logEntry);

            log.info(
                    "SUCCESS saving log | deviceId={} | action={}",
                    saved.getDeviceId(),
                    saved.getAction()
            );

            return saved;

        } catch (Exception e) {

            log.error(
                    "FAILED saving log for deviceId: {}",
                    logEntry.getDeviceId(),
                    e
            );

            throw e;
        }
    }

    /**
     * INSERT sensor reading — called by ESP8266 every N seconds
     *
     * @param sensor the sensor reading
     * @return the saved sensor reading
     */
    @PostMapping("/sensor")
    public SensorReading addSensor(@RequestBody SensorReading sensor) {

        log.info("Adding sensor reading for deviceId: {}", sensor.getDeviceId());

        try {

            SensorReading saved = service.saveSensor(sensor);

            log.info(
                    "SUCCESS saving sensor | deviceId={} | totalPower={}",
                    saved.getDeviceId(),
                    saved.getTotalPower()
            );

            return saved;

        } catch (Exception e) {

            log.error(
                    "FAILED saving sensor for deviceId: {}",
                    sensor.getDeviceId(),
                    e
            );

            throw e;
        }
    }

    /**
     * GET latest sensor reading — called by Vue dashboard
     *
     * @param deviceId the device ID
     * @return the latest sensor reading
     */
    @GetMapping("/sensor/latest/{deviceId}")
    public SensorReading getLatest(@PathVariable String deviceId) {

        log.info("Fetching latest sensor reading for deviceId: {}", deviceId);

        try {

            SensorReading sensor = service.getLatestSensor(deviceId);

            if (sensor == null) {
                log.warn("No latest sensor data found for deviceId: {}", deviceId);
            } else {
                log.info("SUCCESS fetching latest sensor for deviceId: {}", deviceId);
            }

            return sensor;

        } catch (Exception e) {

            log.error(
                    "FAILED fetching latest sensor for deviceId: {}",
                    deviceId,
                    e
            );

            throw e;
        }
    }

    /**
     * GET latest sensor history for chart
     *
     * @param deviceId the device ID
     * @return list of sensor readings
     */
    @GetMapping("/sensor/chart/{deviceId}")
    public List<SensorReading> getChartData(@PathVariable String deviceId) {

        log.info("Fetching chart data for deviceId: {}", deviceId);

        try {

            List<SensorReading> chartData = service.getChartData(deviceId);

            log.info(
                    "SUCCESS fetching chart data | deviceId={} | totalData={}",
                    deviceId,
                    chartData.size()
            );

            return chartData;

        } catch (Exception e) {

            log.error(
                    "FAILED fetching chart data for deviceId: {}",
                    deviceId,
                    e
            );

            throw e;
        }
    }

    /**
     * GET latest electricity token
     *
     * @param deviceId the device ID
     * @return latest electricity token
     */
    @GetMapping("/token/{deviceId}")
    public ElectricityToken getToken(@PathVariable String deviceId) {

        log.info("Fetching token for deviceId: {}", deviceId);

        return service.getLatestToken(deviceId);
    }
}