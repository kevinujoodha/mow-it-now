package com.xebia.test.services;

import com.xebia.test.beans.Command;
import com.xebia.test.beans.Mower;
import com.xebia.test.beans.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.xebia.test.beans.CardinalPoint.*;
import static com.xebia.test.beans.Command.*;

public class MowerCommandServiceTest {

    private static final int LIMIT_X = 5;
    private static final int LIMIT_Y = 5;

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_mower_is_null() {
        MowerCommandService.INSTANCE.executeCommand(null, Command.FORWARD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_command_is_null() {
        MowerCommandService.INSTANCE.executeCommand(new Mower(null, 0, 0), (Command) null);
    }

    @Test
    public void should_execute_a_list_of_command_when_commands_are_correct() {
        Mower mower = new Mower(new Position(1, 2, NORTH), LIMIT_X, LIMIT_Y);

        MowerCommandService.INSTANCE.executeCommand(
                mower,
                Arrays.asList(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD)
        );

        Assert.assertEquals(new Position(1, 3, NORTH), mower.getPosition());
    }

    @Test
    public void should_return_the_correct_direction_when_turning_right() {
        Mower mower = new Mower(new Position(0, 0, NORTH), LIMIT_X, LIMIT_Y);

        MowerCommandService.INSTANCE.executeCommand(mower, RIGHT);
        Assert.assertEquals(new Position(0, 0, EAST), mower.getPosition());

        MowerCommandService.INSTANCE.executeCommand(mower, RIGHT);
        Assert.assertEquals(new Position(0, 0, SOUTH), mower.getPosition());

        MowerCommandService.INSTANCE.executeCommand(mower, RIGHT);
        Assert.assertEquals(new Position(0, 0, WEST), mower.getPosition());

        MowerCommandService.INSTANCE.executeCommand(mower, RIGHT);
        Assert.assertEquals(new Position(0, 0, NORTH), mower.getPosition());
    }

    @Test
    public void should_return_the_correct_direction_when_turning_left() {
        Mower mower = new Mower(new Position(0, 0, NORTH), LIMIT_X, LIMIT_Y);

        MowerCommandService.INSTANCE.executeCommand(mower, LEFT);
        Assert.assertEquals(new Position(0, 0, WEST), mower.getPosition());

        MowerCommandService.INSTANCE.executeCommand(mower, LEFT);
        Assert.assertEquals(new Position(0, 0, SOUTH), mower.getPosition());

        MowerCommandService.INSTANCE.executeCommand(mower, LEFT);
        Assert.assertEquals(new Position(0, 0, EAST), mower.getPosition());

        MowerCommandService.INSTANCE.executeCommand(mower, LEFT);
        Assert.assertEquals(new Position(0, 0, NORTH), mower.getPosition());
    }

    /**
     * NORTH TESTS
     */

    @Test
    public void should_move_to_correct_direction_when_moving_to_north() {
        Mower mower = new Mower(new Position(2, 2, NORTH), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(2, 3, NORTH), mower.getPosition());
    }

    @Test
    public void should_not_move_to_north_when_is_at_the_limit() {
        Mower mower = new Mower(new Position(2, LIMIT_Y, NORTH), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(2, LIMIT_Y, NORTH), mower.getPosition());
    }

    /**
     * EAST TESTS
     */

    @Test
    public void should_move_to_correct_direction_when_moving_to_east() {
        Mower mower = new Mower(new Position(2, 2, EAST), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(3, 2, EAST), mower.getPosition());
    }

    @Test
    public void should_not_move_to_east_when_is_at_the_limit() {
        Mower mower = new Mower(new Position(LIMIT_X, 2, EAST), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(LIMIT_X, 2, EAST), mower.getPosition());
    }

    /**
     * SOUTH TESTS
     */

    @Test
    public void should_move_to_correct_direction_when_moving_to_south() {
        Mower mower = new Mower(new Position(2, 2, SOUTH), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(2, 1, SOUTH), mower.getPosition());
    }

    @Test
    public void should_not_move_to_south_when_is_at_the_limit() {
        Mower mower = new Mower(new Position(2, 0, SOUTH), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(2, 0, SOUTH), mower.getPosition());
    }

    /**
     * WEST TESTS
     */

    @Test
    public void should_move_to_correct_direction_when_moving_to_west() {
        Mower mower = new Mower(new Position(2, 2, WEST), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(1, 2, WEST), mower.getPosition());
    }

    @Test
    public void should_not_move_to_west_when_is_at_the_limit() {
        Mower mower = new Mower(new Position(0, 2, WEST), LIMIT_X, LIMIT_Y);
        MowerCommandService.INSTANCE.executeCommand(mower, FORWARD);
        Assert.assertEquals(new Position(0, 2, WEST), mower.getPosition());
    }

}