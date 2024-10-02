package com.eastflag.nnc.user.repository;

import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.dto.UserSearchDto;
import com.eastflag.nnc.user.model.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<UserDto> findAllBySearch(UserSearchDto userSearchDto, Pageable pageable) {
        var user = QUser.user;

        var content =  jpaQueryFactory
                // DTO로 변환
                .select(Projections.fields(UserDto.class, user.id, user.email, user.nickname, user.role, user.created, user.updated))
                .from(user)
                // 동적 쿼리 email like '%조건%' and nickname like '%조건'
                .where(emailLike(userSearchDto.getEmail()),
                        nicknameLike(userSearchDto.getNickname()))
                .offset(pageable.getOffset()) // 몇 번째 페이지부터 시작할 것 인지.
                .limit(pageable.getPageSize()) // 페이지당 몇개의 데이터를 보여줄껀지
                .fetch();

        var countQuery = jpaQueryFactory
                .select(user.count())
                .from(user)
                .where(emailLike(userSearchDto.getEmail()),
                        nicknameLike(userSearchDto.getNickname()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression emailLike(String email) {
        return StringUtils.hasText(email) ? QUser.user.email.contains(email) : null;
    }

    private BooleanExpression nicknameLike(String nickname) {
        return StringUtils.hasText(nickname) ? QUser.user.nickname.contains(nickname) : null;
    }
}
