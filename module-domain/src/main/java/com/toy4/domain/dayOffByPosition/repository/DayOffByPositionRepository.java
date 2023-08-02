package com.toy4.domain.dayOffByPosition.repository;

import com.toy4.domain.dayOffByPosition.domain.DayOffByPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayOffByPositionRepository extends JpaRepository<DayOffByPosition, Long> {

    Optional<DayOffByPosition> findByPositionId(Long id);
}
