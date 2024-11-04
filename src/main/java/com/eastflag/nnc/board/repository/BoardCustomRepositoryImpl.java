package com.eastflag.nnc.board.repository;

import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardSearchDto;
import com.eastflag.nnc.board.model.QBoard;
import com.eastflag.nnc.board_category.model.QBoardCategory;
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
public class BoardCustomRepositoryImpl implements BoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<BoardDto> findBoardByCategory(BoardSearchDto boardSearchDto, Pageable pageable) {
        var board = QBoard.board;
        var boardCategory = QBoardCategory.boardCategory;

        var content = jpaQueryFactory
                .select(Projections.fields(BoardDto.class, board.id, board.title, board.created, board.updated))
                .from(board)
                .innerJoin(board.boardCategory, boardCategory)
                .where(boardCategory.name.eq(boardSearchDto.getCategoryName()),
                        titleLike(boardSearchDto.getTitle()))
                .offset(pageable.getOffset()) // 몇 번째 페이지부터 시작할 것 인지.
                .limit(pageable.getPageSize()) // 페이지당 몇개의 데이터를 보여줄껀지
                .fetch();

        var countQuery = jpaQueryFactory
                .select(board.count())
                .from(board)
                .where(boardCategory.name.eq(boardSearchDto.getCategoryName()),
                        titleLike(boardSearchDto.getTitle()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }


    private BooleanExpression titleLike(String title) {
        return StringUtils.hasText(title) ? QBoard.board.title.contains(title) : null;
    }
}
