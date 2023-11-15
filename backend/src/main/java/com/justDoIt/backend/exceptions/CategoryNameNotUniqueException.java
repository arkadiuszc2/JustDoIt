package com.justDoIt.backend.exceptions;

public class CategoryNameNotUniqueException extends ServiceLayerException{

  public CategoryNameNotUniqueException(String message) {
    super(message);
  }
}
