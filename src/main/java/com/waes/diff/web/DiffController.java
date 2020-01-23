package com.waes.diff.web;

import com.waes.diff.domain.DiffSide;
import com.waes.diff.repository.DiffRepository;
import com.waes.diff.service.DiffCheckerService;
import com.waes.diff.service.DiffService;
import com.waes.diff.web.dto.DataDTO;
import com.waes.diff.web.dto.DiffResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;


/**
 * REST service controller for diff operations
 */
@RestController
@RequestMapping("/v1/diff/{diffId}")
public class DiffController {
    private static final Logger logger = LoggerFactory.getLogger(DiffController.class);

    @Autowired
    private DiffRepository diffRepository;

    @Autowired
    private DiffCheckerService diffCheckerService;

    @Autowired
    private DiffService diffService;


    /**
     * @param diffId
     * @param leftSide
     */
    @PostMapping(path = "/left")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Add Left base64 encoded binary data")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "OK"), @ApiResponse(responseCode = "400", description = "Bad request not a valid base64")})
    public void addLeftSide(@PathVariable(value = "diffId") int diffId, @RequestBody @Validated DataDTO leftSide) {
        logger.info("POST /left diffId : " + diffId);
        diffService.addorUpdateSide(diffId, DiffSide.LEFT, leftSide.getData());
    }

    /**
     * @param diffId
     * @param rightSide
     */
    @PostMapping(path = "/right")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Add Right base64 encoded binary data")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "OK"), @ApiResponse(responseCode = "400", description = "Bad request not a valid base64")})
    public void addRightSide(@PathVariable(value = "diffId") int diffId, @RequestBody @Validated DataDTO rightSide) {
        logger.info("POST /right diffId : " + diffId);
        diffService.addorUpdateSide(diffId, DiffSide.RIGHT, rightSide.getData());
    }

    /**
     * @param diffId
     * @return diffResultDTO
     */
    @Operation(description = "Get Diff Result of left and right base64 encoded binary data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Left or Right sides not found")
    })
    @GetMapping
    public DiffResultDTO findDiffResource(@PathVariable(value = "diffId") int diffId) {
        logger.info("GET /diff diffId : " + diffId);
        DiffResultDTO diffResultDTO = diffService.findDiffResource(diffId);
        return diffResultDTO;
    }

    /**
     * @param ex
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(NoSuchElementException.class)
    public void return404(NoSuchElementException ex, HttpServletResponse response) throws IOException {
        logger.error("Unable to complete not found ", ex);
        response.sendError(HttpStatus.NOT_FOUND.value(), "Unable to complete not found");
    }


    /**
     * @param ex
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public void return400(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        logger.error("Unable to complete illegal argument", ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Unable to complete illegal argument");
    }

    /**
     * @param ex
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, HttpServletResponse response) throws IOException {
        logger.error("Unable to complete internal error", ex);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to complete internal error");
    }

}