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

  public static <T> ResponseEntity<ApiResponse<T>> error(String code, String errorMessage) {
    return ResponseEntity.ok(ApiResponse.<T>builder()
        .success(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  public static <T> ResponseEntity<ApiResponse<T>> badRequest(String code, String errorMessage) {
    return ResponseEntity.badRequest().body(ApiResponse.<T>builder()
        .success(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  public static <T> ResponseEntity<ApiResponse<T>> serverError(String code, String errorMessage) {
    return ResponseEntity.status(500).body(ApiResponse.<T>builder()
        .success(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public record Error(String errorCode, String errorMessage) {

    public static Error of(String errorCode, String errorMessage) {
      return new Error(errorCode, errorMessage);
    }
  }
}