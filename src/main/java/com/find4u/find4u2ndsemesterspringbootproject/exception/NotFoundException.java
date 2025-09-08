// 📦 Package declaration - this class belongs to the 'exception' folder/package
package com.find4u.find4u2ndsemesterspringbootproject.exception;

// 🚨 Custom Exception Class - used when a requested resource is not found
public class NotFoundException extends RuntimeException {
     
     // ⚠️ Constructor that accepts a custom error message
     // Eg: "User with ID U001 not found"
     public NotFoundException(String message) {
          super(message); // 🧠 Call the parent class (RuntimeException) constructor with that message
     }
}

//Why use this?
//Because instead of returning a generic 500 error, you can throw this manually to say something more specific like:

//throw new NotFoundException("User not found with ID: " + userId);
//Then your GlobalExceptionHandler can catch it and return a proper API response like:
//
//{
//     "status": 404,
//     "message": "User not found with ID: U001",
//     "data": null
//}


