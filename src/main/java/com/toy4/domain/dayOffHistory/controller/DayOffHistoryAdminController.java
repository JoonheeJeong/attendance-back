package com.toy4.domain.dayOffHistory.controller;

import com.toy4.domain.dayOffHistory.dto.request.DayOffHistoryRequest;
import com.toy4.domain.dayOffHistory.service.DayOffHistoryService;
import com.toy4.global.response.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/day-offs")
@RequiredArgsConstructor
@RestController
public class DayOffHistoryAdminController {

	private final DayOffHistoryService dayOffHistoryService;

	@GetMapping
	public ResponseEntity<?> getDayOffs() {
		CommonResponse<?> response = dayOffHistoryService.getDayOffs();
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<?> updateStatusDayOff(
		@Validated @RequestBody DayOffHistoryRequest request) {
		CommonResponse<?> response = dayOffHistoryService.updateStatusDayOff(DayOffHistoryRequest.to(request));
		return ResponseEntity.ok(response);
	}


}
