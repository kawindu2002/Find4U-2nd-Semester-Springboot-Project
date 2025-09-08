// This class is inside the 'exception' package (used for custom exceptions)
package com.find4u.find4u2ndsemesterspringbootproject.exception;

// Custom Exception: thrown when a resource already exists (duplicate entry error)
public class AllReadyFoundException extends RuntimeException {
  
  // Constructor that takes a message like:
  // "User with email john@gmail.com already exists"
  public AllReadyFoundException(String message) {
    super(message); // Pass the custom message to the RuntimeException constructor
  }
}

