package kr.co.chan.week2.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
  NOT_FOUND_USER("유저를 찾을 수 없습니다."),
  // ... 다른 예외 코드들
  DUPLICATED_USER("이미 가입된 이메일입니다."),
  NOT_FOUND_COMMA("sortBy는 필드명, 방향 형식이어야 합니다.");

  final String message;
}
