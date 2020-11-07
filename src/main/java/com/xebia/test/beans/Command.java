package com.xebia.test.beans;

import java.util.Arrays;

public enum Command {
    LEFT("G"), RIGHT("D"), FORWARD("A");

    private final String label;

    Command(String label) {
        this.label = label;
    }

    public static Command fromLabel(String label) {
        return Arrays.stream(values())
                .filter(c -> c.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(label + " is not a valid command"));
    }
}
