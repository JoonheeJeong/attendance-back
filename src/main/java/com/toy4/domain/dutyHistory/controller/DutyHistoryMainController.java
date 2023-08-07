package com.toy4.domain.dutyHistory.controller;

import com.toy4.domain.dutyHistory.dto.DutyCancellationRequest;
import com.toy4.domain.dutyHistory.dto.DutyRegistrationRequest;
import com.toy4.domain.dutyHistory.exception.DutyHistoryException;
import com.toy4.domain.dutyHistory.service.DutyHistoryMainService;
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
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class DutyHistoryMainController {

    private final DutyHistoryMainService dutyHistoryMainService;
    private final ResponseService responseService;

    @PostMapping("/schedules/duty")
    public ResponseEntity<?> requestDutyRegistration(
            @Valid @RequestBody DutyRegistrationRequest requestBody,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("[POST /api/schedules/duty] errors");
            bindingResult.getAllErrors().forEach(objectError -> log.info("{}", objectError));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseService.failure(ErrorCode.INVALID_REQUEST));
        }

        dutyHistoryMainService.registerDuty(requestBody);

        return ResponseEntity.created(URI.create("/api/schedules"))
                .body(responseService.success(null, SuccessCode.SUCCESS));
    }

    @PutMapping("/schedules/duty/{dutyId}/status")
    public ResponseEntity<?> requestDutyCancellation(
            @PathVariable("dutyId") Long dutyHistoryId,
            @Valid @RequestBody DutyCancellationRequest requestBody,
            BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            log.error("[error] PUT /api/schedules/duty/{}/status: {}", dutyHistoryId, bindingResult.getFieldErrors());
            String errorMessage = getErrorMessageFromBindingResult(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseService.failure(errorMessage));
        }

        dutyHistoryMainService.cancelDutyRegistrationRequest(dutyHistoryId, requestBody);

        return ResponseEntity.ok(responseService.success(null, SuccessCode.SUCCESS));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleDutyHistoryException(DutyHistoryException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(responseService.failure(e.getErrorCode()));
    }

    /** bindingResult ErrorMessage 반환 */
    private String getErrorMessageFromBindingResult(BindingResult bindingResult) {
        return Objects.requireNonNull(bindingResult.getFieldError())
                .getDefaultMessage();
    }
}
