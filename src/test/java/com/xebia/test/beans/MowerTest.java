package com.xebia.test.beans;

import org.junit.Test;

public class MowerTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_coordinates_exceed_the_limit() {
        new Mower(new Position(80, 4, null), 5, 5);
    }

}