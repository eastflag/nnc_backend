package com.eastflag.nnc.board_category.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardCategoryDto {
    private Integer id;
    private String name;
    private Boolean useYn;
}
