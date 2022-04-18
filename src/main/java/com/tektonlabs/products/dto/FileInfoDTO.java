package com.tektonlabs.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileInfoDTO {
    private String method;
    private Long startTime;
    private Long endTime;
    private Long duration;
}
