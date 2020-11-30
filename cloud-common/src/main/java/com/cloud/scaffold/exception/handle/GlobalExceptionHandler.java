package com.cloud.scaffold.exception.handle;

import com.cloud.scaffold.exception.ScaffoldException;
import com.cloud.scaffold.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.cloud.scaffold")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof ScaffoldException) {
            ScaffoldException exception = (ScaffoldException)e;
            return CommonResult.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
        }
        return CommonResult.unknownError();
    }

}