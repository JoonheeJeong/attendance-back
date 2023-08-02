package com.toy4.domain.department.repository;

import com.toy4.domain.department.domain.Department;
import com.toy4.domain.department.type.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByType(DepartmentType type);
}
