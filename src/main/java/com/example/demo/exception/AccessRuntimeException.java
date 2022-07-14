package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.RedirectView;

@Getter
@Setter
public class AccessRuntimeException extends RuntimeException {
    private HttpStatus httpStatus;
    private RedirectView redirectView;

    public AccessRuntimeException(String message, HttpStatus httpStatus, RedirectView redirectView) {
        super(message);
        this.httpStatus = httpStatus;
        this.redirectView = redirectView;
    }

    public AccessRuntimeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
