package kr.co.chan.week2.domain.user.service;

import kr.co.chan.week2.common.exception.ServiceException;
import kr.co.chan.week2.common.exception.ServiceExceptionCode;
import kr.co.chan.week2.domain.user.dto.UserCreateRequest;
import kr.co.chan.week2.domain.user.dto.UserResponse;
import kr.co.chan.week2.domain.user.entity.User;
import kr.co.chan.week2.domain.user.mapper.UserMapper;
import kr.co.chan.week2.domain.user.repository.UserQueryRepository;
import kr.co.chan.week2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  private final UserRepository userRepository;
  private final UserQueryRepository userQueryRepository;

  @Transactional
  public UserResponse create(UserCreateRequest request) {
    if(userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATED_USER);
    }

    String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
    request.setPassword(hashedPassword); //비밀번호 암호화

    User user = userRepository.save(userMapper.toEntity(request));

    return userMapper.toResponse(user);

  }

}
