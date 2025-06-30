package kr.co.chan.week2.domain.user.controller;

import jakarta.validation.Valid;
import kr.co.chan.week2.common.response.ApiResponse;
import kr.co.chan.week2.domain.user.dto.UserCreateRequest;
import kr.co.chan.week2.domain.user.dto.UserResponse;
import kr.co.chan.week2.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
    return ApiResponse.success( userService.create(request));
  }

}
