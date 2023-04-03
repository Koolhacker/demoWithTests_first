package com.example.demowithtests.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        //ErrorDetails errorDetails = new ErrorDetails(new Date(), "Author not found with id =" + request.getParameter("id"), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceWasDeletedException.class)
    protected ResponseEntity<MyGlobalExceptionHandler> handleDeleteException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler("This user was deleted"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<MyGlobalExceptionHandler> idNotFoundException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAbsentException.class)
    protected ResponseEntity<MyGlobalExceptionHandler> dataAbsentException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Not enough data"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTypeIdException.class)
    protected ResponseEntity<MyGlobalExceptionHandler> wrongTypeIdException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Wrong type of data in ID"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    private static class MyGlobalExceptionHandler {
        private String message;
    }
}
