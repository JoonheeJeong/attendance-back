package com.toy4.domain.dayOffHistory;

import com.toy4.domain.dayOffHistory.service.DayOffHistoryMainService;
import com.toy4.domain.dayoff.exception.DayOffException;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;
import com.toy4.global.response.type.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class DayOffHistoryMainController {

    private final DayOffHistoryMainService dayOffHistoryMainService;
    private final ResponseService responseService;

    @PostMapping("/schedules/day-off")
    public ResponseEntity<?> requestDayOffRegistration(
            @Valid @RequestBody DayOffRegistrationRequest requestBody,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("[POST /api/schedules/day-off] errors: {}", bindingResult.getFieldErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseService.failure(ErrorCode.INVALID_REQUEST));
        }

        dayOffHistoryMainService.registerDayOff(requestBody.toDto());

        return ResponseEntity.created(URI.create("/api/schedules"))
                .body(responseService.success(null, SuccessCode.SUCCESS));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleDayOffException(DayOffException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(responseService.failure(e.getErrorCode()));
    }
}
