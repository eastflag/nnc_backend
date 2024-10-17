package com.eastflag.nnc.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ImageDTO {
    private Integer id;
    private String type;
    private byte[] image;
    private String dataUri; // RFC2397
    private String url;
}
