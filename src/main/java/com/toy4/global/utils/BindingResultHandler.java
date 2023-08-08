package com.toy4.global.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Locale;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class BindingResultHandler {

    private final MessageSource ms;

    /** bindingResult ErrorMessage 반환 */
    public String getErrorMessageFromBindingResult(BindingResult bindingResult) {
        String ret = null;
        FieldError fieldError = Objects.requireNonNull(bindingResult.getFieldError());
        for (String code : Objects.requireNonNull(fieldError.getCodes())) {
            try {
                String msg = ms.getMessage(code, fieldError.getArguments(), Locale.getDefault());
                log.info("code: {}, message: {}", code, msg);
                if (ret == null)
                    ret = msg;
            } catch (NoSuchMessageException ignored) {
            }
        }
        return ret;
    }
}
