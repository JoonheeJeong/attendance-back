package com.toy4.domain.employee.service;

import com.toy4.domain.employee.dto.EmployeeDto;
import com.toy4.global.response.dto.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    // 이메일 중복 확인
    CommonResponse<?> validateUniqueEmail(String email);

    // 회원가입
    CommonResponse<?> signup(EmployeeDto request, MultipartFile profileImage);

    // 로그인
    CommonResponse<?> login(EmployeeDto request);
  
    CommonResponse<?> updateEmployeeInfo(EmployeeDto employeeDto, MultipartFile file);
    CommonResponse<?> getEmployeeInfo(Long id);
    CommonResponse<?> getMyPage(Long id);
}
