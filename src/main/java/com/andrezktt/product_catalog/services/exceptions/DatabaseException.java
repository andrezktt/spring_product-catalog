package com.andrezktt.product_catalog.services.exceptions;

public class DatabaseException extends RuntimeException {
  public DatabaseException(String message) {
    super(message);
  }
}
