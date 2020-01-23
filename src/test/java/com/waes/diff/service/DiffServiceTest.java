package com.waes.diff.service;

import com.waes.diff.domain.ResultType;
import com.waes.diff.repository.DiffRepository;
import com.waes.diff.service.impl.DiffServiceImpl;
import com.waes.diff.web.dto.DiffOffsetInfo;
import com.waes.diff.domain.Diff;
import com.waes.diff.web.dto.DiffResultDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by Gungor Tumak
 */
@RunWith(MockitoJUnitRunner.class)
public class DiffServiceTest {

    private static final int DIFF_ID = 1;
    private static final byte[] LEFT = {1, 2, 3};
    private static final byte[] RIGHT = {1, 2, 3};


    @Mock
    private DiffRepository diffRepository;

    @Mock
    private DiffCheckerService diffCheckerService;

    @InjectMocks
    private DiffServiceImpl diffService;

    @Mock
    private Diff diffMock;

    @Mock
    private DiffOffsetInfo diffOffsetInfo;

    @Mock
    private DiffResultDTO diffResultDTO;

    /**
     * Mock responses
     */
    @Before
    public void setValues() {
        when(diffRepository.findById(DIFF_ID)).thenReturn(Optional.of(diffMock));
//        when(diffMock.getId()).thenReturn(DIFF_ID);
        when(diffMock.getLeftSide()).thenReturn(LEFT);
        when(diffMock.getRightSide()).thenReturn(RIGHT);
//        when(diffCheckerService.compareSides(LEFT,RIGHT)).thenReturn(Arrays.asList(diffOffsetInfo));
//        when(diffResultDTO.getResult()).thenReturn(ResultType.EQUALS);
    }


    /**
     * findDiff
     */
    @Test
    public void findDiff() {
        when(diffRepository.findById(DIFF_ID)).thenReturn(Optional.of(diffMock));

        assertThat(diffService.findDiffResource(DIFF_ID).getResult(), is(ResultType.EQUALS));
    }


}