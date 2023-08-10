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

@RequestMapping("/api/admin/day-offs")
@RequiredArgsConstructor
@RestController
public class DayOffHistoryAdminController {

	private final DayOffHistoryService dayOffHistoryService;
	private final ResponseService responseService;

	@GetMapping
	public ResponseEntity<?> getDayOffs() {
		List<DayOffHistoriesResponse> dayOffs = dayOffHistoryService.getDayOffs();
		return ResponseEntity.ok(responseService.success(dayOffs));
	}

	@PutMapping
	public ResponseEntity<?> updateStatusDayOff(
		@Validated @RequestBody DayOffHistoryRequest request) {
		CommonResponse<?> response = dayOffHistoryService.updateStatusDayOff(DayOffHistoryRequest.to(request));
		return ResponseEntity.ok(response);
	}


}
