package kr.co.chan.week2.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends RuntimeException {

  String code;
  String message;
  HttpStatus status;

  public ServiceException(ServiceExceptionCode response) {
    super(response.getMessage());
    this.code = response.name();
    this.message = super.getMessage();
    this.status = response.getHttpStatus();
  }

  @Override
  public String getMessage() {
    return message;
  }
}