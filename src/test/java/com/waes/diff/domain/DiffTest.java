package com.waes.diff.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Gungor Tumak.
 */
public class DiffTest {

    @Test
    public void testLeftSide() {

        Diff diff = new Diff();
        byte data[] = {35,36};
        diff.setLeftSide(data);
        assertThat(diff.getId(), is(nullValue()));
        assertThat(diff.getLeftSide(), is(data));
        assertThat(diff.getRightSide(), is(nullValue()));
    }

    @Test
    public void testRightSide() {

        Diff diff = new Diff();
        byte data[] = {35,36};
        diff.setRightSide(data);
        assertThat(diff.getId(), is(nullValue()));
        assertThat(diff.getLeftSide(), is(nullValue()));
        assertThat(diff.getRightSide(), is(data));
    }

    @Test
    public void testBothSide() {

        Diff diff = new Diff();
        byte data[] = {35,36};
        diff.setRightSide(data);
        diff.setLeftSide(data);
        assertThat(diff.getId(), is(nullValue()));
        assertThat(diff.getLeftSide(), is(data));
        assertThat(diff.getRightSide(), is(data));
    }
}
