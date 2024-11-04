package com.eastflag.nnc.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
}
