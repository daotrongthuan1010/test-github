package thayduc.quanlydancu.demo.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private HttpStatus status;
    private String message;

    public ExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
