package kr.co.chan.week2.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @NotNull
  String name;

  @Email
  String email;

  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-])[A-Za-z\\d!@#$%^&*()_+=-]{8,20}$",
      message = "비밀번호는 8~20자이며, 영문자, 숫자, 특수문자를 각각 최소 1개 이상 포함해야 합니다."
  )
  String password;

}
