package com.eastflag.nnc.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findAllBySearch() {
        return jpaQueryFactory
                .selectFrom(QUser.user)
                .fetch();
    }
}
