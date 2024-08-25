package io.artsok.scratchgame.exception;

public class GenericProcessorException extends RuntimeException {

  public GenericProcessorException(String message, Throwable cause) {
    super(message, cause);
  }

  public GenericProcessorException(String message) {
    super(message);
  }
}
