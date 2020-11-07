package com.xebia.test.beans;

import java.util.Arrays;

public enum CardinalPoint {
    NORTH("N") {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getY() < mower.getLimitY()) {
                mower.getPosition().setY(mower.getPosition().getY() + 1);
            }
        }
    },

    EAST("E") {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getX() < mower.getLimitX()) {
                mower.getPosition().setX(mower.getPosition().getX() + 1);
            }
        }
    },

    WEST("W") {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getX() > 0) {
                mower.getPosition().setX(mower.getPosition().getX() - 1);
            }
        }
    },

    SOUTH("S") {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getY() > 0) {
                mower.getPosition().setY(mower.getPosition().getY() - 1);
            }
        }
    };

    private final String label;

    CardinalPoint(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CardinalPoint fromLabel(String label) {
        return Arrays.stream(values())
                .filter(c -> c.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(label + " is not a valid cardinal point"));
    }

    public abstract void move(Mower mower);
}
