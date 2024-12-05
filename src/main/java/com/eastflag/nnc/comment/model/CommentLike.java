package com.eastflag.nnc.comment.model;

import com.eastflag.nnc.common.BaseEntity;
import com.eastflag.nnc.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_like")
public class CommentLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 호감도: 좋아요 1, 싫어요 -1, 좋아요 취소 or 싫어요 취소 0
    private Integer likeability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
