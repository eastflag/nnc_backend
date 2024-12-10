package com.eastflag.nnc.comment.model;

import com.eastflag.nnc.board.model.Board;
import com.eastflag.nnc.common.BaseEntity;
import com.eastflag.nnc.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parent_id;

    // 사용자에 의한 삭제
    @ColumnDefault("false")
    private Boolean isDeleted;

    // 신고 등으로 차단
    @ColumnDefault("false")
    private Boolean isBlocked;

    @Column(length=300, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
