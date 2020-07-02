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

    protected Actor getPlayerNearby() {
        for (int i = -1; i < 2; i++) {
            Cell cell = getCell().getNeighbor(i, 0);
            if (cell.getActor() instanceof Player) {
                return cell.getActor();
            }
        }
        for (int j = -1; j < 2; j++) {
            Cell cell = getCell().getNeighbor(0, j);
            if (cell.getActor() instanceof Player) {
                return cell.getActor();
            }
        }
        return null;
    }
}
