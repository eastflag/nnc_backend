package com.eastflag.nnc.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BoardSearchDto {
    private String title;
    @JsonProperty("category_name")
    private String categoryName;
}
