package com.example.transactionorder.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionOrderControllerAdvice {

    @ExceptionHandler({ProductException.class})
    public ResponseEntity<ErrorResponse> handleProductException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({OrderException.class})
    public ResponseEntity<ErrorResponse> handleOrderException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse {
        private String reason;
    }
}
