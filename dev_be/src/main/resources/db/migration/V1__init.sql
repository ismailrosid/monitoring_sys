-- Create sensor_readings table
CREATE TABLE sensor_readings (
id BIGSERIAL PRIMARY KEY,
device_id TEXT,
timestamp TIMESTAMP,

lamp_voltage FLOAT,
lamp_current FLOAT,
lamp_power FLOAT,
lamp_energy FLOAT,
lamp_pf FLOAT,
lamp_freq FLOAT,

fan_voltage FLOAT,
fan_current FLOAT,
fan_power FLOAT,
fan_energy FLOAT,
fan_pf FLOAT,
fan_freq FLOAT,

total_power FLOAT,
total_energy FLOAT
);

-- Create device_logs table
CREATE TABLE device_logs (
id BIGSERIAL PRIMARY KEY,
device_id TEXT,
device TEXT,
action TEXT,
trigger TEXT,
triggered_by TEXT,
note TEXT,
timestamp TIMESTAMP
);

-- Create device_statuses table
CREATE TABLE device_statuses (
    device_id TEXT PRIMARY KEY,
    device_name TEXT,

    lamp_on BOOLEAN,
    lamp_last_changed TIMESTAMP,

    fan_on BOOLEAN,
    fan_last_changed TIMESTAMP,

    pir_entry_detected BOOLEAN,
    pir_entry_last_detected TIMESTAMP,

    pir_exit_detected BOOLEAN,
    pir_exit_last_detected TIMESTAMP,

    esp_last_ping TIMESTAMP,

    ip_address TEXT,
    firmware_version TEXT,

    updated_at TIMESTAMP
);

-- CREATE TABLE electricity_tokens (
-- id BIGSERIAL PRIMARY KEY,

-- device_id VARCHAR(100) NOT NULL,

-- nominal_rupiah DOUBLE PRECISION NOT NULL,

-- tariff_per_kwh DOUBLE PRECISION NOT NULL,

-- total_kwh DOUBLE PRECISION NOT NULL,

-- used_kwh DOUBLE PRECISION DEFAULT 0,

-- remaining_kwh DOUBLE PRECISION NOT NULL,

-- created_at TIMESTAMP DEFAULT NOW(),

-- updated_at TIMESTAMP DEFAULT NOW()
-- );

CREATE TABLE electricity_token_histories (
    id BIGSERIAL PRIMARY KEY,

    device_id VARCHAR(100) NOT NULL,

    nominal_rupiah DOUBLE PRECISION NOT NULL,

    tariff_per_kwh DOUBLE PRECISION NOT NULL,

    purchased_kwh DOUBLE PRECISION NOT NULL,

    note TEXT,

    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_token_history_device_id
ON electricity_token_histories(device_id);

CREATE INDEX idx_token_history_created_at
ON electricity_token_histories(created_at);
-- DROP TABLE IF EXISTS device_logs CASCADE;
-- DROP TABLE IF EXISTS sensor_readings CASCADE;
-- DROP TABLE IF EXISTS electricity_tokens CASCADE;
-- DROP TABLE IF EXISTS device_statuses CASCADE;
-- DROP TABLE IF EXISTS flyway_schema_history CASCADE;