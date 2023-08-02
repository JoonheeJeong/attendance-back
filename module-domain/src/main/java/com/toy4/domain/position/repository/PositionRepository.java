package com.toy4.domain.position.repository;

import com.toy4.domain.position.domain.Position;
import com.toy4.domain.position.type.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByType(PositionType type);
}
