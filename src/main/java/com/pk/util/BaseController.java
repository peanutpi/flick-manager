package com.pk.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorInfo exceptionResolver(Exception e) {
        return new ErrorInfo("BAD_REQUEST", e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorInfo rsourceNotFound(Exception e) {
        return new ErrorInfo("NOT_FOUND", e.getMessage());
    }

    public static class ErrorInfo {
        private String error;
        private String errorDescription;

        public ErrorInfo(String error, String errorDescription) {
            this.error = error;
            this.errorDescription = errorDescription;
        }

        public String getError() {
            return error;
        }

        public String getErrorDescription() {
            return errorDescription;
        }
    }

    public static class ResourceNotFoundException extends Exception {

        private static final long serialVersionUID = -2357747868399266385L;

        public ResourceNotFoundException(String message) {
            super(message);
        }

        public ResourceNotFoundException(String message, Exception e) {
            super(message, e);
        }

    }

}
