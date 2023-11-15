package com.justDoIt.backend.exceptions;


public class TaskNotFoundException extends ServiceLayerException{

  public TaskNotFoundException(String message) {
    super(message);
  }
}
