package com.xebia.test.beans;

public class Mower {
    private final Position position;
    private final int limitX;
    private final int limitY;

    public Mower(Position position, int limitX, int limitY) {
        this.position = position;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    public Position getPosition() {
        return position;
    }

    public int getLimitX() {
        return limitX;
    }

    public int getLimitY() {
        return limitY;
    }
}