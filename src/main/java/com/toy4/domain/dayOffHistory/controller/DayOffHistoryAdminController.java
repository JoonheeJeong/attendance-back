package com.toy4.domain.dayOffHistory.controller;

import com.toy4.domain.dayOffHistory.dto.request.DayOffHistoryRequest;
import com.toy4.domain.dayOffHistory.dto.response.DayOffHistoriesResponse;
import com.toy4.domain.dayOffHistory.service.DayOffHistoryService;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RequiredArgsConstructor
@RestController
public class DayOffHistoryAdminController {

	private final DayOffHistoryService dayOffHistoryService;
	private final ResponseService responseService;

	@GetMapping("/day-offs")
	public ResponseEntity<?> getDayOffs() {
		List<DayOffHistoriesResponse> dayOffs = dayOffHistoryService.getDayOffs();
		return ResponseEntity.ok(responseService.success(dayOffs));
	}

	@PutMapping("/day-offs")
	public ResponseEntity<?> updateStatusDayOff(
		@Validated @RequestBody DayOffHistoryRequest request) {
		CommonResponse<?> response = dayOffHistoryService.updateStatusDayOff(DayOffHistoryRequest.to(request));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/employees/{employeeId}/day-offs")
	public ResponseEntity<?> getEmployeeApprovedDayOff(@PathVariable Long employeeId) {
		CommonResponse<?> response = dayOffHistoryService.getEmployeeApprovedDayOff(employeeId);
		return ResponseEntity.ok(response);
	}
}
