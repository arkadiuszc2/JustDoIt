package com.justDoIt.backend.exceptions;

public class CategoryNotFoundException extends ServiceLayerException{

  public CategoryNotFoundException(String message) {
    super(message);
  }
}
