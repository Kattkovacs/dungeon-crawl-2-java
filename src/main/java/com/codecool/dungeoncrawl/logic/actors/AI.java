package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class AI extends Actor {
    public AI(Cell cell) {
        super(cell);
    }

    @Override
    public void die() {
        getCell().setActor(null);
    }
}
