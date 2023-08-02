package com.toy4.domain.department.service.impl;

import com.toy4.domain.department.domain.Department;
import com.toy4.domain.department.repository.DepartmentRepository;
import com.toy4.domain.department.service.DepartmentService;
import com.toy4.domain.department.type.DepartmentType;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.toy4.global.response.type.SuccessCode.SUCCESS;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ResponseService responseService;

    @Override
    public CommonResponse<?> insertAllDepartments() {
        List<Department> departments = departmentRepository.saveAll(
                Arrays.stream(DepartmentType.values())
                        .map(departmentType -> Department.builder().type(departmentType).build())
                        .collect(Collectors.toList()));

        return responseService.success(departments, SUCCESS);
    }
}
