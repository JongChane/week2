package kr.co.chan.week2.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {

  private boolean success;
  private T data;
  private Error error;

  public static <T> ApiResponse<T> success() {
    return success(null);
  }

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .success(true)
        .data(data)
        .build();
  }

  public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
    return ResponseEntity.status(201).body(ApiResponse.<T>builder()
        .success(true)
        .data(data)
        .build());
  }

  public static <T> ResponseEntity<ApiResponse<T>> of(
      int statusCode,
      boolean success,
      T data,
      Error error
  ) {
    return ResponseEntity.status(statusCode).body(ApiResponse.<T>builder()
        .success(success)
        .data(data)
        .error(error)
        .build());
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public record Error(String errorCode, String errorMessage) {

    public static Error of(String errorCode, String errorMessage) {
      return new Error(errorCode, errorMessage);
    }
  }
}