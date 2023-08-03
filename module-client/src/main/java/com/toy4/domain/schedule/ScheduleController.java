package com.toy4.domain.schedule;

import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final ScheduleMainService scheduleMainService;
    private final ResponseService responseService;

    @GetMapping
    public CommonResponse<?> getSchedules(
            @Valid @RequestBody ScheduleRequest dto) {

        Long employeeId = dto.getEmployeeId();
        ScheduleResponse scheduleResponse = scheduleMainService.getSchedules(employeeId);

        return responseService.success(scheduleResponse, SuccessCode.SUCCESS);
    }

}
