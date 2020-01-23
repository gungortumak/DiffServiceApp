package com.waes.diff.web;

import com.waes.diff.domain.Diff;
import com.waes.diff.domain.ResultType;
import com.waes.diff.repository.DiffRepository;
import com.waes.diff.web.dto.DiffResultDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class DiffControllerTest {


    private static final int DIFF_ID = 1;
    private static final byte[] LEFT = {1, 2, 3};
    private static final byte[] RIGHT = {4, 5, 6};
    private static final String URL = "/v1/diff/" + DIFF_ID;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DiffRepository diffRepository;

    @Mock
    private Diff diffMock;

    @Before
    public void setValues() {
        Diff diff = Diff.builder().id(DIFF_ID).leftSide(LEFT).rightSide(RIGHT).build();
        diffRepository.save(diff);
    }


    /**
     * HTTP GET /v1/diff/{diffId}
     */
    @Test
    public void getDiff() {
        ResponseEntity<DiffResultDTO> response =
                restTemplate.getForEntity(URL, DiffResultDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getResult(), is(ResultType.EQUAL_SIZE_DIFFERENT_CONTENT));

    }
}
