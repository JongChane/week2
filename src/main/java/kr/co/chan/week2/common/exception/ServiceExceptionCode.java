package kr.co.chan.week2.common.exception;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  // 404 에러
  NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
  NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리 입니다."),
  NOT_FOUND_COMMA(HttpStatus.BAD_REQUEST, "sortBy는 필드명, 방향 형식이어야 합니다."),

  // 400 에러
  INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "상품의 재고가 부족합니다."),
  DUPLICATED_USER(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다."),
  DUPLICATED_PRODUCT_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 상품명입니다.");

  final HttpStatus httpStatus;
  final String message;
}
