package com.wtwlc.web_experiment.exception;

import com.wtwlc.web_experiment.common.BaseResponse;
import com.wtwlc.web_experiment.common.ErrorCode;
import com.wtwlc.web_experiment.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?>businessExceptionHandler(BusinessException e){
        log.error("BusinessException:",e);
        return ResultUtils.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?>runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException:",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage());
    }
}
