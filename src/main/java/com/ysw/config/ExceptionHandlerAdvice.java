package com.ysw.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler(value
      = { IllegalArgumentException.class, IllegalStateException.class, RuntimeException.class })
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
  }

}
