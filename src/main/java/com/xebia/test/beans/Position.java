package com.xebia.test.beans;

import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private CardinalPoint cardinalPoint;

    public Position(int x, int y, CardinalPoint cardinalPoint) {
        this.x = x;
        this.y = y;
        this.cardinalPoint = cardinalPoint;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }

    public void setCardinalPoint(CardinalPoint cardinalPoint) {
        this.cardinalPoint = cardinalPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y &&
                cardinalPoint == position.cardinalPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, cardinalPoint);
    }

    @Override
    public String toString() {
        return String.join(
                " ",
                String.valueOf(x),
                String.valueOf(y),
                cardinalPoint == null ? "" : cardinalPoint.getLabel()
        );
    }
}
