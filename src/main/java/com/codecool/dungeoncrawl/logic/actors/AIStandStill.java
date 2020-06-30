package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public abstract class AIStandStill extends Actor {

    public AIStandStill(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {}

}
