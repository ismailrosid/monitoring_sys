-- Create sensor_readings table
CREATE TABLE sensor_readings (
                                 id           BIGSERIAL PRIMARY KEY,
                                 device_id    TEXT,
                                 timestamp    TIMESTAMP,

                                 lamp_voltage FLOAT,
                                 lamp_current FLOAT,
                                 lamp_power   FLOAT,
                                 lamp_energy  FLOAT,
                                 lamp_pf      FLOAT,
                                 lamp_freq    FLOAT,

                                 fan_voltage  FLOAT,
                                 fan_current  FLOAT,
                                 fan_power    FLOAT,
                                 fan_energy   FLOAT,
                                 fan_pf       FLOAT,
                                 fan_freq     FLOAT,

                                 total_power  FLOAT,
                                 total_energy FLOAT
);

-- Create device_logs table
CREATE TABLE device_logs (
                             id           BIGSERIAL PRIMARY KEY,
                             device_id    TEXT,
                             device       TEXT,
                             action       TEXT,
                             trigger      TEXT,
                             triggered_by TEXT,
                             note         TEXT,
                             timestamp    TIMESTAMP
);

-- Create device_statuses table
CREATE TABLE device_statuses (
                                 device_id         TEXT PRIMARY KEY,

                                 lamp_on           BOOLEAN,
                                 lamp_last_changed TIMESTAMP,

                                 fan_on            BOOLEAN,
                                 fan_last_changed  TIMESTAMP,

                                 pir_detected      BOOLEAN,
                                 pir_last_detected TIMESTAMP,

                                 esp_online        BOOLEAN,
                                 esp_last_ping     TIMESTAMP,
                                 ip_address        TEXT,
                                 firmware_version  TEXT,

                                 updated_at        TIMESTAMP
);