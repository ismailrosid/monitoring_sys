package com.musala.devbe.repository;

import com.musala.devbe.entity.ElectricityToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectricityTokenRepository extends JpaRepository<ElectricityToken, Long> {

    Optional<ElectricityToken> findTopByDeviceIdOrderByCreatedAtDesc(String deviceId);
}