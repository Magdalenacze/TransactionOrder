package com.example.transactionorder.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class TransactionOrderControllerAdvice {

    @ExceptionHandler({ProductException.class})
    public ResponseEntity<ErrorResponse> handleProductException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String methodArgumentNotValidException(Exception e) {
        return e.getMessage();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidParameterException.class)
    public String invalidParameterException(InvalidParameterException e){
        return e.getMessage();
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse {
        private String reason;
    }
}
