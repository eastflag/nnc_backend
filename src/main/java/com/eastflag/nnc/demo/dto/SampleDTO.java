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
public class SampleDTO {
    @Min(value = 10000, message = "price는 10000보다 큰 값이어여 합니다")
    private Integer price;

    @NotNull(message = "name은 필수값입니다")
    private String name;

    @Size(min = 5, max = 10, message = "maker는 5 ~ 10 사이 값입니다")
    private String maker;
}
