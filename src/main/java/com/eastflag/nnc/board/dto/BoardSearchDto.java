package com.eastflag.nnc.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardSearchDto {
    private String title;
    private String categoryName;
}
