package com.eastflag.nnc.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    @JsonProperty("category_id")  // requestRequestBody에서 매핑
    private Integer categoryId;
    @JsonProperty("user_id")
    private Integer userId;
    private LocalDateTime created;
    private LocalDateTime updated;
}
