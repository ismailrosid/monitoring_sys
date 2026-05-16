CREATE TABLE electricity_tokens (
                                    id BIGSERIAL PRIMARY KEY,

                                    device_id VARCHAR(100) NOT NULL,

                                    nominal_rupiah DOUBLE PRECISION NOT NULL,

                                    tariff_per_kwh DOUBLE PRECISION NOT NULL,

                                    total_kwh DOUBLE PRECISION NOT NULL,

                                    used_kwh DOUBLE PRECISION DEFAULT 0,

                                    remaining_kwh DOUBLE PRECISION NOT NULL,

                                    created_at TIMESTAMP DEFAULT NOW(),

                                    updated_at TIMESTAMP DEFAULT NOW()
);

-- Dummy token awal
INSERT INTO electricity_tokens (
    device_id,
    nominal_rupiah,
    tariff_per_kwh,
    total_kwh,
    used_kwh,
    remaining_kwh
) VALUES (
             'esp8266-musala-01',
             5000,
             1444.70,
             3.46,
             0,
             3.46
         );