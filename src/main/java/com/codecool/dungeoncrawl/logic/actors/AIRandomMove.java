package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.RandomUtil;

public abstract class AIRandomMove extends AI {

    public AIRandomMove(Cell cell) {
        super(cell);
    }

    private Actor getPlayerNearby() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cell cell = getCell().getNeighbor(i, j);
                if (cell.getActor() instanceof Player) {
                    return cell.getActor();
                }
            }
        }
        return null;
    }

    @Override
    public void move(int dx, int dy) {
        if (getPlayerNearby() != null) {
            fight(getPlayerNearby());
        } else {
            Cell nextCell = getCell();
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
            if (nextCell.canMoveThrough() && nextCell.getActor() == null) {
                getCell().setActor(null);
                nextCell.setActor(this);
                setCell(nextCell);
            }
        }
    }

}
