package com.toy4.domain.status.repository;

import com.toy4.domain.status.domain.Status;
import com.toy4.domain.status.type.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findByType(StatusType type);
}
