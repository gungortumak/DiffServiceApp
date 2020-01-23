package com.waes.diff.web.dto;

import com.waes.diff.domain.ResultType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 */
@Data
@NoArgsConstructor
public class DiffResultDTO {
    private ResultType result;
    private List<DiffOffsetInfo> diffInformation;
}
