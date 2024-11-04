package com.eastflag.nnc.board.repository;

import com.eastflag.nnc.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
