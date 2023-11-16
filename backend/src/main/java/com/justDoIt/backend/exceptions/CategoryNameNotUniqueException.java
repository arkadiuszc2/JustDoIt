package com.justDoIt.backend.exceptions;

public class CategoryNameNotUniqueException extends ServiceNotFoundException {

  public CategoryNameNotUniqueException(String message) {
    super(message);
  }
}
