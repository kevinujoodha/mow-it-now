package com.xebia.test;

import com.xebia.test.beans.Mower;
import com.xebia.test.services.MowerBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MowerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MowerApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting mowers deployment . . .");

        try {
            Path path = checkFile(args);

            List<String> lines = Files.lines(path)
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toList());

            List<Mower> mowers = MowerBatchService.INSTANCE.executeBatch(lines);
            LOGGER.info("End position of mowers : ");
            mowers.forEach(mower -> LOGGER.info(mower.getPosition().toString()));
            LOGGER.info("End of mowers deployment");
        } catch (Exception e) {
            LOGGER.error("Cannot deploy mowers", e);
        }
    }

    private static Path checkFile(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("You must give a file path in argument");
        }

        Path path = Paths.get(args[0]);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("The file \"" + path + "\" does not exist");
        }
        return path;
    }
}
