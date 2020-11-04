package com.xebia.test.services;

import com.xebia.test.beans.CardinalPoint;
import com.xebia.test.beans.Position;
import com.xebia.test.exceptions.UnreadableMowerFileException;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.xebia.test.beans.Command.*;
import static org.junit.Assert.assertEquals;

public class MowerFileServiceTest {

    @Test(expected = UnreadableMowerFileException.class)
    public void should_throw_an_exception_when_the_position_is_unreadable() throws UnreadableMowerFileException {
        MowerFileService.INSTANCE.readPosition("bad format");
    }

    @Test(expected = UnreadableMowerFileException.class)
    public void should_throw_an_exception_when_the_coordinates_are_negative() throws UnreadableMowerFileException {
        MowerFileService.INSTANCE.readPosition("-5 5 N");
    }

    @Test(expected = UnreadableMowerFileException.class)
    public void should_throw_an_exception_when_the_cardinal_is_incorrect() throws UnreadableMowerFileException {
        MowerFileService.INSTANCE.readPosition("1 1 BAD");
    }

    @Test
    public void should_read_the_limits_when_the_format_is_correct() throws UnreadableMowerFileException {
        assertEquals(
                new Position(3, 6, null),
                MowerFileService.INSTANCE.readPosition("3 6")
        );
    }

    @Test
    public void should_read_the_position_when_the_format_is_correct() throws UnreadableMowerFileException {
        assertEquals(
                new Position(7, 9, CardinalPoint.NORTH),
                MowerFileService.INSTANCE.readPosition("7 9 N")
        );

        assertEquals(
                new Position(4, 8, CardinalPoint.EAST),
                MowerFileService.INSTANCE.readPosition("4 8 E")
        );

        assertEquals(
                new Position(15, 20, CardinalPoint.SOUTH),
                MowerFileService.INSTANCE.readPosition("15 20 S")
        );

        assertEquals(
                new Position(1, 2, CardinalPoint.WEST),
                MowerFileService.INSTANCE.readPosition("1 2 W")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_an_exception_when_commands_are_incorrect() {
        MowerFileService.INSTANCE.readCommands("bad format");
    }

    @Test
    public void should_read_the_commands_when_the_format_is_correct() {
        assertEquals(
                Arrays.asList(FORWARD, RIGHT, RIGHT, LEFT, FORWARD),
                MowerFileService.INSTANCE.readCommands("ADDGA")
        );
    }
}