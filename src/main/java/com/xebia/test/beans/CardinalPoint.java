package com.xebia.test.beans;

public enum CardinalPoint {
    NORTH {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getY() < mower.getLimitY()) {
                mower.getPosition().setY(mower.getPosition().getY() + 1);
            }
        }
    },

    EAST {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getX() < mower.getLimitX()) {
                mower.getPosition().setX(mower.getPosition().getX() + 1);
            }
        }
    },

    WEST {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getX() > 0) {
                mower.getPosition().setX(mower.getPosition().getX() - 1);
            }
        }
    },

    SOUTH {
        @Override
        public void move(Mower mower) {
            if (mower.getPosition().getY() > 0) {
                mower.getPosition().setY(mower.getPosition().getY() - 1);
            }
        }
    };

    public abstract void move(Mower mower);
}
