package com.waes.diff.service;

import com.waes.diff.web.dto.DiffOffsetInfo;

import java.util.List;

/**
 * Service for comparing sides data
 */
public interface DiffCheckerService {
    List<DiffOffsetInfo> compareSides(byte[] left, byte[] right);
}
