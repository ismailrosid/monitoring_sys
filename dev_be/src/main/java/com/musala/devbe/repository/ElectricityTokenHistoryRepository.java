package com.musala.devbe.repository;

import com.musala.devbe.entity.ElectricityTokenHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ElectricityTokenHistoryRepository
        extends JpaRepository<ElectricityTokenHistory, Long> {

        @Query("""
        SELECT e
        FROM ElectricityTokenHistory e
        WHERE e.deviceId = :deviceId
        AND YEAR(e.createdAt) = YEAR(CURRENT_DATE)
        AND MONTH(e.createdAt) = MONTH(CURRENT_DATE)
        """)
    List<ElectricityTokenHistory> findCurrentMonthByDeviceId(
            @Param("deviceId") String deviceId
    );
}