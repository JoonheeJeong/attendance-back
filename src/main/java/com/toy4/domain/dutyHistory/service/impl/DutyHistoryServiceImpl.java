package com.toy4.domain.dutyHistory.service.impl;

import static com.toy4.global.response.type.SuccessCode.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy4.domain.dutyHistory.domain.DutyHistory;
import com.toy4.domain.dutyHistory.dto.ApprovedDutyResponse;
import com.toy4.domain.dutyHistory.dto.DutyHistoriesResponse;
import com.toy4.domain.dutyHistory.dto.DutyHistoryDto;
import com.toy4.domain.dutyHistory.exception.DutyHistoryException;
import com.toy4.domain.dutyHistory.repository.DutyHistoryRepository;
import com.toy4.domain.dutyHistory.service.DutyHistoryService;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.schedule.RequestStatus;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DutyHistoryServiceImpl implements DutyHistoryService {

	private final EmployeeRepository employeeRepository;
	private final DutyHistoryRepository dutyHistoryRepository;
	private final ResponseService responseService;

	@Override
	@Transactional
	public CommonResponse<?> getEmployeeApprovedDuty(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));

		List<DutyHistory> approveDuties = dutyHistoryRepository.findByEmployeeIdAndStatus(employee.getId(),
			RequestStatus.APPROVED);

		if (approveDuties.isEmpty()) {
			return responseService.failure(ErrorCode.EMPLOYEE_APPROVED_DUTY_NOT_FOUND);
		}

		List<ApprovedDutyResponse> responses = approveDuties.stream()
			.map(ApprovedDutyResponse::from)
			.collect(Collectors.toList());

		return responseService.successList(responses, SUCCESS);
	}

	@Override
	@Transactional(readOnly = true)
	public CommonResponse<?> getDutyHistories() {
		List<DutyHistory> dutyHistories = dutyHistoryRepository.findAll();

		if (dutyHistories.isEmpty()) {
			return responseService.failure(ErrorCode.DUTY_HISTORIES_NOT_FOUND);
		}

		List<DutyHistoriesResponse> responses = dutyHistories.stream()
			.map(DutyHistoriesResponse::from)
			.collect(Collectors.toList());

		return responseService.successList(responses, SUCCESS);
	}

	@Override
	public CommonResponse<?> updateStatusDuty(DutyHistoryDto dto) {
		DutyHistory dutyHistory = dutyHistoryRepository.findById(dto.getId())
			.orElseThrow(() -> new DutyHistoryException(ErrorCode.ENTITY_NOT_FOUND));

		dutyHistory.updateStatusDuty(dto);
		dutyHistoryRepository.save(dutyHistory);

		return responseService.success(dto.getId(), SUCCESS);
	}
}
