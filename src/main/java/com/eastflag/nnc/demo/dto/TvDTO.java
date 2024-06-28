package com.eastflag.nnc.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class TvDTO {
    @Min(10000)
    private Integer price;
    @NotNull
    private String name;
    @Size(min = 5, max = 10)
    private String maker;
}
