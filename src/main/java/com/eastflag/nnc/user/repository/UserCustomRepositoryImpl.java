package com.eastflag.nnc.user.repository;

import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.model.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserDto> findAllBySearch() {
        var user = QUser.user;

        return jpaQueryFactory
                .select(Projections.fields(UserDto.class, user.id, user.email, user.nickname, user.role))
                .from(user)
                .fetch();
    }
}
