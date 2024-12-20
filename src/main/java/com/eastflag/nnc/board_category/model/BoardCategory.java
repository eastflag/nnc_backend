package com.eastflag.nnc.board_category.model;

import com.eastflag.nnc.board.model.Board;
import com.eastflag.nnc.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board_category")
public class BoardCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Board 엔티티에 boardCategory 속성에 의해서 맾핑된다.
    @OneToMany(mappedBy = "boardCategory")
    private List<Board> boards;

    @Column(unique = true, nullable = false)
    private String name;

    @ColumnDefault("true")
    private Boolean useYn;
}
