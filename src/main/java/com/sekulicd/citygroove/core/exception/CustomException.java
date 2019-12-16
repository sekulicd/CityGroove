package com.sekulicd.citygroove.core.exception;

public class CustomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;

  public CustomException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
