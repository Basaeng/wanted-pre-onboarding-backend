package backend.preonboarding.wanted.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class PageControllerAdvice {

    private ErrorResponse errorResponse;

    @ExceptionHandler(InsertFailException.class)
    public ResponseEntity<ErrorResponse> handleInsertFailedException(InsertFailException e) {
        log.error("Insert Fail Exception " + e.getMessage());
        errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
