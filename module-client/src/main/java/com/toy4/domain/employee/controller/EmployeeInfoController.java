package com.toy4.domain.employee.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.domain.employee.dto.request.PersonalInfoRequest;
import com.toy4.global.response.dto.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Log4j2
public class EmployeeInfoController {

	private final EmployeeService employeeService;

	@GetMapping(path="/personal-info/{id}")
	public ResponseEntity<?> getPersonalInfo(@PathVariable Long id) {
		CommonResponse<?> response = employeeService.getEmployeeInfo(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping(path="/personal-info",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updatePersonalInfo(
		@Validated @RequestPart PersonalInfoRequest personalInfoRequest,
		@RequestPart(required=false) MultipartFile profile) {

		CommonResponse<?> response = employeeService.updateEmployeeInfo(PersonalInfoRequest.to(personalInfoRequest), profile);
		return ResponseEntity.ok(response);
	}

	@GetMapping(path="/my-page/{id}")
	public ResponseEntity<?> getMyPage(@PathVariable Long id) {
		CommonResponse<?> response = employeeService.getMyPage(id);
		return ResponseEntity.ok(response);
	}
}
