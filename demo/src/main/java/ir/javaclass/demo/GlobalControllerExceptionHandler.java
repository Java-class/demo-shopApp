package ir.javaclass.demo;

import exception.InsufficientBalanceException;
import exception.ProductNotFoundException;
import exception.UserNotFoundException;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GlobalControllerExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResponse> handleException(Exception ex) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<JsonResponse> handleParserException(ParseException ex) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<JsonResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<JsonResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<JsonResponse> handleBalanceFoundException(InsufficientBalanceException ex) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(ex.getMessage()), HttpStatus.INSUFFICIENT_STORAGE);
    }

    private class JsonResponse {
        String message;

        public JsonResponse() {
        }

        public JsonResponse(String message) {
            super();
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}