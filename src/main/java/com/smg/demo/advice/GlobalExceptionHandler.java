package com.smg.demo.advice;

import com.smg.demo.exception.NoRecordFound;
import com.smg.demo.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(Exception ex) {
    return new ErrorResponse(ex.getMessage());
  }


  @ExceptionHandler(NoRecordFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNoRecordFound(Exception ex) {
    return new ErrorResponse(ex.getMessage());
  }
}
