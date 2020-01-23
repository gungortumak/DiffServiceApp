package com.waes.diff.service.impl;

import com.waes.diff.domain.Diff;
import com.waes.diff.domain.DiffSide;
import com.waes.diff.domain.ResultType;
import com.waes.diff.repository.DiffRepository;
import com.waes.diff.service.DiffCheckerService;
import com.waes.diff.service.DiffService;
import com.waes.diff.web.DiffController;
import com.waes.diff.web.dto.DiffOffsetInfo;
import com.waes.diff.web.dto.DiffResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service Implemantion for diff operations
 */
@Service
public class DiffServiceImpl implements DiffService {

    private static final Logger logger = LoggerFactory.getLogger(DiffController.class);

    @Autowired
    private DiffRepository diffRepository;

    @Autowired
    private DiffCheckerService diffCheckerService;


    /**
     * @param diffId
     * @param diffSide
     * @param sideData
     * @throws IllegalArgumentException
     */
    @Override
    @CacheEvict(value = "diffResult", key = "#diffId")
    public void addorUpdateSide(Integer diffId, DiffSide diffSide, String sideData) throws IllegalArgumentException {
        Diff diff = findOrBuildDiff(diffId);
        if (diffSide == diffSide.LEFT) {
            diff.setLeftSide(Base64.getDecoder().decode(sideData));
        } else if (diffSide == diffSide.RIGHT) {
            diff.setRightSide(Base64.getDecoder().decode(sideData));
        } else {
            throw new IllegalArgumentException("Side is not valid" + diffSide);
        }
        diffRepository.save(diff);
    }


    /**
     * @param diffId
     * @return
     */
    @Override
    @Cacheable(value = "diffResult", key = "#diffId")
    public DiffResultDTO findDiffResource(Integer diffId) {
        Diff diff = verifyDiff(diffId);
        DiffResultDTO diffResultDTO = new DiffResultDTO();
        List<DiffOffsetInfo> diffOffsetInfoList = new ArrayList<>();
        if(Arrays.equals(diff.getLeftSide(),diff.getRightSide())){
            logger.debug("GET /diff" + ResultType.EQUALS, diffId);
            diffResultDTO.setResult(ResultType.EQUALS);
        }else if (diff.getLeftSide().length != diff.getRightSide().length){
            logger.debug("GET /diff" + ResultType.NOT_EQUAL_SIZE, diffId);
            diffResultDTO.setResult(ResultType.NOT_EQUAL_SIZE);
        }else {
            logger.debug("GET /diff" + ResultType.EQUAL_SIZE_DIFFERENT_CONTENT, diffId);
            diffResultDTO.setResult(ResultType.EQUAL_SIZE_DIFFERENT_CONTENT);
            diffOffsetInfoList = diffCheckerService.compareSides(diff.getLeftSide(),diff.getRightSide());
        }
        diffResultDTO.setDiffInformation(diffOffsetInfoList);

        return diffResultDTO;
    }

    /**
     * @param diffId
     * @return Diff
     */
    private Diff findOrBuildDiff(Integer diffId) {
        logger.info("findOrPrepareDiff diffId : " + diffId);
        return diffRepository.findById(diffId).orElse(Diff.builder().id(diffId).build());
    }


    /**
     * @param diffId
     * @return
     * @throws NoSuchElementException
     */
    private Diff verifyDiff(Integer diffId) throws NoSuchElementException {
        logger.info("verifyDiff diffId : " + diffId);
        Diff diff = diffRepository.findById(diffId).orElseThrow(() -> new NoSuchElementException("Diff does not exist " + diffId));
        if(diff.getLeftSide() == null){
            throw(new NoSuchElementException("Left side does not exist " + diffId));
        }
        if(diff.getRightSide() == null){
            throw(new NoSuchElementException("Right side does not exist " + diffId));
        }
        return diff;
    }

}
