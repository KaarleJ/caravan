package org.caravan.exception;

public class InvalidClientSecretException extends RuntimeException {
  public InvalidClientSecretException(String message) {
    super(message);
  }
}