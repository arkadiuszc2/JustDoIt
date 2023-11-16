package com.justDoIt.backend.exceptions;

public class WrongIdFormatException extends ServiceLayerException{

  public WrongIdFormatException(String message) {
    super(message);
  }
}
