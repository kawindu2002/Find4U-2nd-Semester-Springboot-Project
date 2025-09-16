
package com.find4u.find4u2ndsemesterspringbootproject.exception;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
     
     @ExceptionHandler(AccountNotVerifiedException.class)
     public ResponseEntity<APIResponse> handleAccountNotVerifiedException(AccountNotVerifiedException ex) {
          APIResponse response = new APIResponse(403, ex.getMessage(), null);
          return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
     }
     
     // You can add handlers for other exceptions here
}
