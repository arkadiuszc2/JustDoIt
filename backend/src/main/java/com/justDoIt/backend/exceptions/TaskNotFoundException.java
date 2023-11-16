package com.justDoIt.backend.exceptions;


public class TaskNotFoundException extends ServiceNotFoundException {

  public TaskNotFoundException(String message) {
    super(message);
  }
}
