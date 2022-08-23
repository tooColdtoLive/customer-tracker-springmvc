package com.luv2code.customerTracker.exception;

import com.luv2code.customerTracker.response.CustomerErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex){
        CustomerErrorResponse response = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                                    ex.getMessage(),
                                                                    System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAllException(Exception ex){
        CustomerErrorResponse response = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
                                                                    ex.getMessage(),
                                                                    System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
