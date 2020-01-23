package com.waes.diff.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {
    @NotNull
    private String data;
}
