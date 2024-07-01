package it.epicode.FoodManager.exceptions;

import it.epicode.FoodManager.security.ApiValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {



//    @ExceptionHandler(ApiValidationException.class)
//    public final ResponseEntity<ErrorResponse> handleValidationExceptions(ApiValidationException ex, WebRequest request) {
//        List<String> details = ex.getErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList());
//        ErrorResponse error = new ErrorResponse("Validation Failed", details);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(RuntimeException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Server Error", List.of(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("User Not Found", List.of(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidLoginException(InvalidLoginException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Invalid Login", List.of(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    public static class ErrorResponse {
        private String message;
        private List<String> details;

        public ErrorResponse(String message, List<String> details) {
            super();
            this.message = message;
            this.details = details;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getDetails() {
            return details;
        }
    }
    }


