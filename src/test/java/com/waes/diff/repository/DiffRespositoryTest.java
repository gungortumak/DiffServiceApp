package com.waes.diff.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.waes.diff.domain.Diff;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiffRespositoryTest {

    private static final int DIFF_ID = 1;
    private static final byte[] LEFT = {1, 2, 3};
    private static final byte[] RIGHT = {1, 2, 3};

    @Autowired
    private DiffRepository diffRepository;

    @Before
    public void setValues() {
        Diff diff = Diff.builder().id(DIFF_ID).leftSide(LEFT).rightSide(RIGHT).build();
        diffRepository.save(diff);
    }

    @Test
    public void findById() {
        Optional<Diff> diff = diffRepository.findById(1);
        assertTrue(diff.isPresent());

        diff = diffRepository.findById(2);
        assertFalse(diff.isPresent());
    }

}
