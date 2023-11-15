package com.justDoIt.backend.advice;

import com.justDoIt.backend.exceptions.CategoryNotFoundException;
import com.justDoIt.backend.exceptions.ServiceLayerException;
import com.justDoIt.backend.exceptions.TaskNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({ServiceLayerException.class})
  public Map<String,String> handleServiceExceptions(ServiceLayerException ex){
    Map<String,String> errorMap = new HashMap<>();
    errorMap.put("An error occured: ", ex.getMessage());
    return errorMap;
  }
}
