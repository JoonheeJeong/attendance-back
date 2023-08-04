package com.toy4.domain.dayoff.repository;

import com.toy4.domain.dayoff.domain.DayOff;
import com.toy4.domain.dayoff.type.DayOffType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {

    DayOff findByType(DayOffType type);
}
