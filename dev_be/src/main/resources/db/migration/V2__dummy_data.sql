-- ==============================
-- DEVICE STATUS
-- ==============================

INSERT INTO device_statuses (
    device_id,
    lamp_on,
    fan_on,
    pir_detected,
    esp_online,
    ip_address,
    firmware_version,
    updated_at
) VALUES (
             'esp8266-musala-01',
             true,
             true,
             true,
             true,
             '192.168.1.100',
             '1.0.0',
             NOW()
         );

-- ==============================
-- SENSOR READINGS
-- ==============================

INSERT INTO sensor_readings (
    device_id,
    timestamp,
    lamp_power,
    fan_power,
    total_power
) VALUES
      ('esp8266-musala-01', NOW() - INTERVAL '11 hours', 5.0, 20.0, 25.0),
      ('esp8266-musala-01', NOW() - INTERVAL '10 hours', 4.9, 19.8, 24.7),
      ('esp8266-musala-01', NOW() - INTERVAL '9 hours',  5.1, 20.1, 25.2),
      ('esp8266-musala-01', NOW() - INTERVAL '8 hours',  0.0,  0.0,  0.0),
      ('esp8266-musala-01', NOW() - INTERVAL '7 hours',  5.0, 20.0, 25.0),
      ('esp8266-musala-01', NOW() - INTERVAL '6 hours',  4.9, 19.9, 24.8),
      ('esp8266-musala-01', NOW() - INTERVAL '5 hours',  5.0, 20.0, 25.0),
      ('esp8266-musala-01', NOW() - INTERVAL '4 hours',  5.1, 20.2, 25.3),
      ('esp8266-musala-01', NOW() - INTERVAL '3 hours',  5.0, 20.0, 25.0),
      ('esp8266-musala-01', NOW() - INTERVAL '2 hours',  0.0,  0.0,  0.0),
      ('esp8266-musala-01', NOW() - INTERVAL '1 hours',  5.0, 20.0, 25.0),
      ('esp8266-musala-01', NOW(),                       5.0, 20.0, 25.0);

-- ==============================
-- DEVICE LOGS
-- ==============================

INSERT INTO device_logs (
    device_id,
    device,
    action,
    trigger,
    triggered_by,
    note,
    timestamp
) VALUES
      ('esp8266-musala-01', 'lampu', 'ON',  'MANUAL', 'admin',  'Lampu dinyalakan via dashboard',      NOW() - INTERVAL '11 hours'),
      ('esp8266-musala-01', 'kipas', 'ON',  'PIR',    'HC-SR501', 'Kipas menyala karena ada gerakan',  NOW() - INTERVAL '11 hours'),
      ('esp8266-musala-01', 'lampu', 'OFF', 'MANUAL', 'admin',  'Lampu dimatikan via dashboard',       NOW() - INTERVAL '8 hours'),
      ('esp8266-musala-01', 'kipas', 'OFF', 'PIR',    'HC-SR501', 'Kipas mati karena tidak ada gerakan', NOW() - INTERVAL '8 hours'),
      ('esp8266-musala-01', 'lampu', 'ON',  'PIR',    'HC-SR501', 'Lampu menyala karena ada gerakan',  NOW() - INTERVAL '7 hours'),
      ('esp8266-musala-01', 'kipas', 'ON',  'MANUAL', 'admin',  'Kipas dinyalakan via dashboard',      NOW() - INTERVAL '7 hours'),
      ('esp8266-musala-01', 'lampu', 'OFF', 'PIR',    'HC-SR501', 'Lampu mati karena tidak ada gerakan', NOW() - INTERVAL '2 hours'),
      ('esp8266-musala-01', 'kipas', 'OFF', 'MANUAL', 'admin',  'Kipas dimatikan via dashboard',       NOW() - INTERVAL '2 hours'),
      ('esp8266-musala-01', 'lampu', 'ON',  'MANUAL', 'admin',  'Lampu dinyalakan untuk sholat ashar', NOW() - INTERVAL '1 hours'),
      ('esp8266-musala-01', 'kipas', 'ON',  'PIR',    'HC-SR501', 'Kipas menyala karena ada gerakan',  NOW() - INTERVAL '1 hours');