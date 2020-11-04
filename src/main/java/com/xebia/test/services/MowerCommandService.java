package com.xebia.test.services;

import com.xebia.test.beans.CardinalPoint;
import com.xebia.test.beans.Command;
import com.xebia.test.beans.Mower;

import java.util.Arrays;
import java.util.List;

import static com.xebia.test.beans.CardinalPoint.*;

public enum MowerCommandService {
    INSTANCE;

    private static final List<CardinalPoint> ORDERED_CARDINAL_POINTS = Arrays.asList(NORTH, EAST, SOUTH, WEST);

    public void executeCommand(Mower mower, List<Command> commands) {
        commands.forEach(command -> executeCommand(mower, command));
    }

    public void executeCommand(Mower mower, Command command) {
        checkInputs(mower, command);

        int lastIndex = ORDERED_CARDINAL_POINTS.size() - 1;
        int i = ORDERED_CARDINAL_POINTS.indexOf(mower.getPosition().getCardinalPoint());
        if (command == Command.RIGHT) {
            i = i == lastIndex ? 0 : i + 1;
        } else if (command == Command.LEFT) {
            i = i == 0 ? lastIndex : i - 1;
        } else if (command == Command.FORWARD) {
            mower.getPosition().getCardinalPoint().move(mower);
        }

        mower.getPosition().setCardinalPoint(ORDERED_CARDINAL_POINTS.get(i));
    }

    private void checkInputs(Mower mower, Command command) {
        if (null == mower) {
            throw new IllegalArgumentException("Mower cannot be null when executing a command");
        }

        if (null == command) {
            throw new IllegalArgumentException("Command cannot be null when executing a command");
        }
    }
}
