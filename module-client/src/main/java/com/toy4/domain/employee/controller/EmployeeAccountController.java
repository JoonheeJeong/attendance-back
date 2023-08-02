package com.toy4.domain.employee.controller;

import com.toy4.domain.employee.dto.request.EmailDuplicateCheckRequest;
import com.toy4.domain.employee.dto.request.LoginRequest;
import com.toy4.domain.employee.dto.request.SignupRequest;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.global.aop.EmployeeLock;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class EmployeeAccountController {

    private final EmployeeService employeeService;
    private final ResponseService responseService;

    @PostMapping("/validateEmail")
    public ResponseEntity<?> validateEmail(
            @Valid @RequestBody EmailDuplicateCheckRequest request
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = getErrorMessageFromBindingResult(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseService.failure(errorMessage));
        }

        CommonResponse<?> response = employeeService.validateUniqueEmail(request.getEmail());
        return ResponseEntity.ok(response);
    }

    @EmployeeLock
    @PostMapping(path = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> signup(
//            @Valid @RequestPart SignupRequest request
            @RequestPart SignupRequest request
            , @RequestPart(required=false) MultipartFile profileImageFile
            , BindingResult bindingResult
    ) {
        // @RequestPart -> @Valid 사용 불가, bindingResult 값 사용 전 Exception 처리 -> 수정 필요
//        if (bindingResult.hasErrors()) {
//            String errorMessage = getErrorMessageFromBindingResult(bindingResult);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseService.failure(errorMessage));
//        }

        CommonResponse<?> response = employeeService.signup(SignupRequest.to(request), profileImageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {
        try {
            CommonResponse<?> response = employeeService.login(LoginRequest.to(request));
            return ResponseEntity.ok(response);
        } catch (EmployeeException e) {
            ErrorCode errorCode = e.getErrorCode();
            HttpStatus httpStatus = errorCode.getHttpStatus();
            return ResponseEntity.status(httpStatus).body(responseService.failure(errorCode));
        }
    }

    /** bindingResult ErrorMessage 반환 */
    private String getErrorMessageFromBindingResult(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.get(0).getDefaultMessage();
    }

}
