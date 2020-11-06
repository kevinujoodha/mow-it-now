package com.xebia.test.services;

import com.xebia.test.beans.Command;
import com.xebia.test.beans.Mower;
import com.xebia.test.beans.Position;
import com.xebia.test.exceptions.UnreadableMowerFileException;

import java.util.ArrayList;
import java.util.List;

public enum MowerBatchService {

    INSTANCE;

    public List<Mower> executeBatch(List<String> lines) throws UnreadableMowerFileException {
        Position limitPosition = MowerFileService.INSTANCE.readPosition(lines.get(0));
        List<Mower> result = new ArrayList<>();
        for (int i = 1; i < lines.size() - 1; i += 2) {
            Mower mower = deployMower(lines.get(i), lines.get(i + 1), limitPosition);
            result.add(mower);
        }
        return result;
    }

    private Mower deployMower(String position, String commands, Position limitPosition) throws UnreadableMowerFileException {
        Mower mower = new Mower(
                MowerFileService.INSTANCE.readPosition(position),
                limitPosition.getX(),
                limitPosition.getY()
        );
        List<Command> commandList = MowerFileService.INSTANCE.readCommands(commands);
        MowerCommandService.INSTANCE.executeCommand(mower, commandList);
        return mower;
    }
}
