-- Insert dummy device status
INSERT INTO device_statuses (
    device_id, lamp_on, lamp_last_changed,
    fan_on, fan_last_changed,
    pir_detected, pir_last_detected,
    esp_online, esp_last_ping,
    ip_address, firmware_version, updated_at
) VALUES (
             'esp8266-musala-01', true, NOW(),
             true, NOW(),
             true, NOW(),
             true, NOW(),
             '192.168.1.100', '1.0.0', NOW()
         );

-- Insert dummy sensor readings (12 data untuk chart)
INSERT INTO sensor_readings (
    device_id, timestamp,
    lamp_voltage, lamp_current, lamp_power, lamp_energy, lamp_pf, lamp_freq,
    fan_voltage, fan_current, fan_power, fan_energy, fan_pf, fan_freq,
    total_power, total_energy
) VALUES
      ('esp32-musala-01', NOW() - INTERVAL '11 hours', 220.1, 0.02, 5.0, 0.001, 0.91, 50.0, 220.1, 0.09, 20.0, 0.005, 0.92, 50.0, 25.0, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '10 hours', 219.8, 0.02, 4.9, 0.001, 0.91, 50.0, 219.8, 0.09, 19.8, 0.005, 0.91, 50.0, 24.7, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '9 hours',  220.3, 0.02, 5.1, 0.001, 0.92, 50.0, 220.3, 0.09, 20.1, 0.005, 0.92, 50.0, 25.2, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '8 hours',  220.0, 0.00, 0.0, 0.000, 0.00, 50.0, 220.0, 0.00, 0.0,  0.000, 0.00, 50.0, 0.0,  0.000),
      ('esp32-musala-01', NOW() - INTERVAL '7 hours',  220.2, 0.02, 5.0, 0.001, 0.91, 50.0, 220.2, 0.09, 20.0, 0.005, 0.92, 50.0, 25.0, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '6 hours',  219.9, 0.02, 4.9, 0.001, 0.90, 50.0, 219.9, 0.09, 19.9, 0.005, 0.91, 50.0, 24.8, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '5 hours',  220.1, 0.02, 5.0, 0.001, 0.91, 50.0, 220.1, 0.09, 20.0, 0.005, 0.92, 50.0, 25.0, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '4 hours',  220.4, 0.02, 5.1, 0.001, 0.92, 50.0, 220.4, 0.09, 20.2, 0.005, 0.92, 50.0, 25.3, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '3 hours',  220.0, 0.02, 5.0, 0.001, 0.91, 50.0, 220.0, 0.09, 20.0, 0.005, 0.91, 50.0, 25.0, 0.006),
      ('esp32-musala-01', NOW() - INTERVAL '2 hours',  219.7, 0.00, 0.0, 0.000, 0.00, 50.0, 219.7, 0.00, 0.0,  0.000, 0.00, 50.0, 0.0,  0.000),
      ('esp32-musala-01', NOW() - INTERVAL '1 hours',  220.2, 0.02, 5.0, 0.001, 0.91, 50.0, 220.2, 0.09, 20.0, 0.005, 0.92, 50.0, 25.0, 0.006),
      ('esp32-musala-01', NOW(),                        220.1, 0.02, 5.0, 0.001, 0.91, 50.0, 220.1, 0.09, 20.0, 0.005, 0.92, 50.0, 25.0, 0.006);

-- Insert dummy device logs
INSERT INTO device_logs (
    device_id, device, action, trigger, triggered_by, note, timestamp
) VALUES
      ('esp32-musala-01', 'lampu', 'ON',  'MANUAL', 'admin',  'Lampu dinyalakan via dashboard',        NOW() - INTERVAL '11 hours'),
      ('esp32-musala-01', 'kipas', 'ON',  'PIR',    'sensor', 'Kipas menyala karena ada jamaah',       NOW() - INTERVAL '11 hours'),
      ('esp32-musala-01', 'lampu', 'OFF', 'MANUAL', 'admin',  'Lampu dimatikan via dashboard',         NOW() - INTERVAL '8 hours'),
      ('esp32-musala-01', 'kipas', 'OFF', 'PIR',    'sensor', 'Kipas mati karena tidak ada jamaah',    NOW() - INTERVAL '8 hours'),
      ('esp32-musala-01', 'lampu', 'ON',  'PIR',    'sensor', 'Lampu menyala karena ada jamaah',       NOW() - INTERVAL '7 hours'),
      ('esp32-musala-01', 'kipas', 'ON',  'MANUAL', 'admin',  'Kipas dinyalakan via dashboard',        NOW() - INTERVAL '7 hours'),
      ('esp32-musala-01', 'lampu', 'OFF', 'PIR',    'sensor', 'Lampu mati karena tidak ada jamaah',    NOW() - INTERVAL '2 hours'),
      ('esp32-musala-01', 'kipas', 'OFF', 'MANUAL', 'admin',  'Kipas dimatikan via dashboard',         NOW() - INTERVAL '2 hours'),
      ('esp32-musala-01', 'lampu', 'ON',  'MANUAL', 'admin',  'Lampu dinyalakan untuk sholat ashar',   NOW() - INTERVAL '1 hours'),
      ('esp32-musala-01', 'kipas', 'ON',  'PIR',    'sensor', 'Kipas menyala karena ada jamaah',       NOW() - INTERVAL '1 hours');