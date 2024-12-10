package com.eastflag.nnc.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardLikeDto {
    private Long id;
    private Integer likeability;

    @JsonProperty("user_id")
    private Integer userId;
    private LocalDateTime created;
    private LocalDateTime updated;
}
