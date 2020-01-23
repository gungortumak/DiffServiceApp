package com.waes.diff.service;

import com.waes.diff.domain.DiffSide;
import com.waes.diff.web.dto.DiffResultDTO;

/**
 * Service for diff operations
 */
public interface DiffService {

    void addorUpdateSide(Integer diffId, DiffSide diffSide, String sideData) throws IllegalArgumentException;
    DiffResultDTO findDiffResource(Integer diffId);

}
