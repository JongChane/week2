package kr.co.chan.week2.global.exception;

import io.swagger.v3.oas.annotations.Hidden;
import java.util.concurrent.atomic.AtomicReference;
import kr.co.chan.week2.common.exception.ServiceException;
import kr.co.chan.week2.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final String VALIDATE_ERROR = "VALIDATE_ERROR";
  private final String SERVER_ERROR = "SERVER_ERROR";

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleResponseException(ServiceException ex) {
    return ApiResponse.of(
        ex.getStatus().value(),           // 동적으로 HTTP 상태 코드 반영
        false,
        null,
        ApiResponse.Error.of(ex.getCode(), ex.getMessage())
    );
  }

}
