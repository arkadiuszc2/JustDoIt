package com.justDoIt.backend.advice;

import com.justDoIt.backend.exceptions.CategoryNameNotUniqueException;
import com.justDoIt.backend.exceptions.ServiceNotFoundException;
import com.justDoIt.backend.exceptions.WrongIdFormatException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({CategoryNameNotUniqueException.class})
  public Map<String, String> handleNotUniqueNameExceptions(CategoryNameNotUniqueException ex) {
    return createErrorMap(ex);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public Map<String, String> handleMismatchTypeExceptions(MethodArgumentTypeMismatchException ex) {
    return createErrorMap(new WrongIdFormatException("Provided wrong id format"));
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({WrongIdFormatException.class})
  public Map<String, String> handleWrongFormatExceptions(WrongIdFormatException ex) {
    return createErrorMap(ex);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({ServiceNotFoundException.class})
  public Map<String, String> handleNotFoundExceptions(ServiceNotFoundException ex) {
    return createErrorMap(ex);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
    return errorMap;
  }

  private Map<String,String> createErrorMap(Exception ex){
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("An error occured:", ex.getMessage());
    return errorMap;
  }
}
