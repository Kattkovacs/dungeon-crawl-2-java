package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.RandomUtil;

import java.util.LinkedList;
import java.util.List;

public abstract class AIGhostMove extends AI {

    private int maxRange;

    public AIGhostMove(Cell cell) {
        super(cell);
        maxRange = setRange();
    }

    @Override
    public void move(int dx, int dy) {
        Actor player = getPlayerNearby();
        if (player != null) {
            fight(player);
        } else {
            List<Cell> freeCells = new LinkedList<>();
            int distance = maxRange;
            Cell nextCell;

            for (int x = -distance; x <= distance; x++) {
                for (int y = -distance; y <= distance; y++) {
                    if (getCell().getGameMap().isOnMap(this.getX() + x, this.getY() + y)) {
                        nextCell = getCell().getNeighbor(x, y);
                        if (nextCell.canMoveThrough() && nextCell.getActor() == null) {
                            freeCells.add(nextCell);
                        }
                    }
                }
            }

            Cell destination = freeCells.get(RandomUtil.nextInt(freeCells.size()));
            getCell().setActor(null);
            destination.setActor(this);
            setCell(destination);
        }
    }

    protected abstract int setRange();

}
