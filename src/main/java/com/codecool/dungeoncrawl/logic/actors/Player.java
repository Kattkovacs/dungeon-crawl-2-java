package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) {
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

    public String getTileName() {
        return "player";
    }
}

