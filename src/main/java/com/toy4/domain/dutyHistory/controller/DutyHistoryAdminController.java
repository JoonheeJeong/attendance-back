package com.toy4.domain.dutyHistory.controller;

import com.toy4.domain.dutyHistory.dto.request.DutyStatusUpdateRequest;
import com.toy4.domain.dutyHistory.service.DutyHistoryService;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/admin/duties")
@RequiredArgsConstructor
@RestController
public class DutyHistoryAdminController {

	private final DutyHistoryService dutyHistoryService;
	private final ResponseService responseService;

	@GetMapping
	public ResponseEntity<?> getDuties() {
		CommonResponse<?> response = dutyHistoryService.getDutyHistories();
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<?> respondDutyRegistrationRequest(
		@Valid @RequestBody DutyStatusUpdateRequest requestBody) {

		dutyHistoryService.updateDutyStatus(requestBody.toDto());
		return ResponseEntity.ok(responseService.success());
	}
}
