package com.covid19.common.exception;

import com.covid19.common.messages.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedAccessException extends RuntimeException {
    private static final long serialVersionUID = 1571945318849673209L;

    public UnAuthorizedAccessException() {
        super(ErrorMessage.UNAUTHORIZED_ACCESS);
    }

    public UnAuthorizedAccessException(String message) {
        super(String.format(ErrorMessage.UNAUTHORIZED_ACCESS, message));
    }

}
