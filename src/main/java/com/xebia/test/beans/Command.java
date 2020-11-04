package com.xebia.test.beans;

import java.util.Arrays;

public enum Command {
    LEFT("G"), RIGHT("D"), FORWARD("A");

    private String label;

    Command(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Command fromLabel(String label) {
        return Arrays.stream(values())
                .filter(c -> c.label.equals(label))
                .findAny()
                .orElseThrow();
    }
}
