package com.toy4.domain.dayOffHistory.repository;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayOffHistoryRepository extends JpaRepository<DayOffHistory, Long> {

    List<DayOffHistory> findByEmployeeId(Long employeeId);

    @Query("SELECT s FROM DayOffHistory s " +
            "WHERE s.employee.id = :employeeId " +
            "AND s.startDate <= :endDate " +
            "AND s.endDate >= :startDate")
    List<DayOffHistory> findByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate);
}
