package kr.co.chan.week2.domain.user.mapper;

import kr.co.chan.week2.domain.user.dto.UserCreateRequest;
import kr.co.chan.week2.domain.user.dto.UserResponse;
import kr.co.chan.week2.domain.user.dto.UserSearchResponse;
import kr.co.chan.week2.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponse toResponse(User user);

  UserSearchResponse toSearch(User user);

  @Mapping(target = "passwordHash", source = "password")
  User toEntity(UserCreateRequest request);

}
