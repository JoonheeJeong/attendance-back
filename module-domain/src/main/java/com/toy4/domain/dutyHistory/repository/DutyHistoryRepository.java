package com.toy4.domain.dutyHistory.repository;

import com.toy4.domain.dutyHistory.domain.DutyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DutyHistoryRepository extends JpaRepository<DutyHistory, Long> {
    List<DutyHistory> findByEmployeeId(Long employeeId);
    @Query("SELECT s FROM DutyHistory s " +
            "WHERE s.employee.id = :employeeId " +
            "AND s.date BETWEEN :startDate AND :endDate")
    List<DutyHistory> findByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate);
}
