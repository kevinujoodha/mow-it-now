package com.xebia.test.services;

import com.xebia.test.beans.Mower;
import com.xebia.test.beans.Position;
import com.xebia.test.exceptions.UnreadableMowerFileException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.xebia.test.beans.CardinalPoint.EAST;
import static com.xebia.test.beans.CardinalPoint.NORTH;
import static org.junit.Assert.assertEquals;

public class MowerBatchServiceTest {

    @Test
    public void should_execute_the_series_of_instructions_when_there_are_correct() throws UnreadableMowerFileException {
        List<String> lines = Arrays.asList(
                "5 5",
                "1 2 N",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );

        List<Mower> mowers = MowerBatchService.INSTANCE.executeBatch(lines);
        assertEquals(new Position(1, 3, NORTH), mowers.get(0).getPosition());
        assertEquals(new Position(5, 1, EAST), mowers.get(1).getPosition());
    }

}