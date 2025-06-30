package kr.co.chan.week2.domain.user.repository;

import static kr.co.chan.week2.domain.purchase.entity.QPurchase.purchase;
import static kr.co.chan.week2.domain.user.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.co.chan.week2.domain.user.dto.QUserResponse;
import kr.co.chan.week2.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<UserResponse> findUserByUserId(Long userId, String email, Pageable pageable) {
    List<UserResponse> contents = queryFactory.select(new QUserResponse(
            user.id,
            user.name,
            user.email,
            purchase.createdAt
        ))
        .from(user)
        .join(purchase).on(purchase.user.eq(user))
        .where(
            user.id.eq(userId),
            emailContains(email)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = queryFactory.select(user.count())
        .from(user)
        .join(purchase).on(purchase.user.eq(user))
        .where(
            user.id.eq(userId),
            emailContains(email)
        )
        .fetchOne();

    return new PageImpl<>(contents, pageable, totalCount);
  }

  private BooleanExpression emailContains(String email) {
    return StringUtils.hasText(email) ? user.email.contains(email) : null;
  }
}
