package com.eastflag.nnc.board_category.repository;

import com.eastflag.nnc.board_category.model.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
}
