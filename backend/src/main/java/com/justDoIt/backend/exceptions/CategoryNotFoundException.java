package com.justDoIt.backend.exceptions;

public class CategoryNotFoundException extends ServiceNotFoundException {

  public CategoryNotFoundException(String message) {
    super(message);
  }
}
