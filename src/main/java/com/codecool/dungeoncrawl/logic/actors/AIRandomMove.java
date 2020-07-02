package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.RandomUtil;

public abstract class AIRandomMove extends AI {

    public AIRandomMove(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Actor player = getPlayerNearby();
        if (player != null) {
            fight(player);
        } else {
            Cell nextCell;
            do {
                nextCell = getCell();
                int direction = RandomUtil.nextInt(4);
                switch (direction) {
                    case 0:
                        nextCell = getCell().getNeighbor(0, 1);
                        break;
                    case 1:
                        nextCell = getCell().getNeighbor(0, -1);
                        break;
                    case 2:
                        nextCell = getCell().getNeighbor(-1, 0);
                        break;
                    case 3:
                        nextCell = getCell().getNeighbor(1, 0);
                        break;
                }
            } while (!nextCell.canMoveThrough() || nextCell.getActor() != null);

            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

}
