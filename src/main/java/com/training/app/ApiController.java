package com.training.app;

import com.shiyatsu.logger.ILoggerService;
import com.shiyatsu.logger.impl.LoggerService;
import com.training.app.auth.CustomAuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@Component
public class ApiController {

    private ILoggerService logger = LoggerService.getLoggingService();

    @ExceptionHandler(CustomAuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponseDto customAuthenticationExceptionHandler(CustomAuthenticationException exception) {
        ApiResponseDto dto = new ApiResponseDto();
        dto.setContent(exception.getMessage());
        dto.setError(true);
        logger.error(this, "Authentication exception : " + exception.getMessage(), exception);
        return dto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseDto exceptionHandler(Exception exception) {
        ApiResponseDto dto = new ApiResponseDto();
        dto.setContent(exception);
        dto.setError(true);
        logger.error(this, "Exception : " + exception.getMessage(), exception);
        return dto;
    }

}
