package io.artsok.scratchgame.exception;

public class GenericException extends RuntimeException {

  public GenericException(String message, Throwable cause) {
    super(message, cause);
  }

  public GenericException(String message) {
    super(message);
  }
}
