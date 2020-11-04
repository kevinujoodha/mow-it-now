package com.xebia.test.services;

import com.xebia.test.beans.CardinalPoint;
import com.xebia.test.beans.Command;
import com.xebia.test.beans.Position;
import com.xebia.test.exceptions.UnreadableMowerFileException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MowerFileService {
    INSTANCE;

    private static final String SPACE_SEPARATOR = " ";

    public Position readPosition(String s) throws UnreadableMowerFileException {
        try {
            String[] tokens = s.trim().split(SPACE_SEPARATOR);
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            CardinalPoint cardinalPoint = null;
            if (tokens.length > 2) {
                cardinalPoint = CardinalPoint.fromLabel(tokens[2]);
            }

            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("The provided coordinates must be >= 0");
            }

            return new Position(x, y, cardinalPoint);
        } catch (Exception e) {
            throw new UnreadableMowerFileException("Cannot read the coordinates from line \"" + s + "\"", e);
        }
    }

    public List<Command> readCommands(String s) {
        return Arrays.stream(s.split(""))
                .map(Command::fromLabel)
                .collect(Collectors.toList());
    }
}
