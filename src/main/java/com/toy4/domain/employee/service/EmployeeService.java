package com.toy4.domain.employee.service;

import com.toy4.domain.employee.dto.ChangePassword;
import com.toy4.domain.employee.dto.ResetPassword;
import com.toy4.domain.employee.dto.Signup;
import com.toy4.domain.employee.dto.ValidateMatchPassword;
import com.toy4.domain.employee.dto.response.EmployeeInfo;
import com.toy4.domain.employee.dto.response.PersonalInfo;
import com.toy4.domain.employee.dto.response.SignupResponse;
import com.toy4.global.response.dto.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    // 이메일 중복 확인
    void validateUniqueEmail(String email);

    // 입력받은 비밀번호가 DB와 같은지 확인
    void validateMatchPasswordWithDB(ValidateMatchPassword request, Long EmployeeId);

    // 회원가입
    SignupResponse signup(Signup request);

    // 비밀번호 변경 이메일 전송
    void sendPasswordChangeEmail(String email);

    // 비밀번호 변경(비로그인)
    void resetPassword(ResetPassword request);

    // 비밀번호 변경(로그인)
    void changePassword(ChangePassword request, Long employeeId);

    void updateEmployeeInfo(EmployeeInfo dto, MultipartFile profileImageFile);
  
    CommonResponse<?> getEmployeeInfo(Long id);
  
    CommonResponse<?> getMyPage(Long id);
  
    void updatePersonalInfo(PersonalInfo dto, MultipartFile profileImageFile);
  
    CommonResponse<?> getEmployeeDayOffInfo();

}