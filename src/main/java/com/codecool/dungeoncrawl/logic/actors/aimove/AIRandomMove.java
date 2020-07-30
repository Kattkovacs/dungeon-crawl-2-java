package com.codecool.dungeoncrawl.logic.actors.aimove;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.aimove.AI;
import com.codecool.dungeoncrawl.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

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
            Cell nextCell = this.getCell();
            List<Cell> directions = new ArrayList<>();
            directions.add(getCell().getNeighbor(0, 1));
            directions.add(getCell().getNeighbor(0, -1));
            directions.add(getCell().getNeighbor(-1, 0));
            directions.add(getCell().getNeighbor(1, 0));
            do {
                int randomIndex = RandomUtil.nextInt(directions.size());
                Cell investigateCell = directions.get(randomIndex);
                if (investigateCell.canMoveThrough() && investigateCell.getActor() == null) {
                    nextCell = investigateCell;
                } else {
                    directions.remove(randomIndex);
                }
            } while (nextCell == this.getCell() && directions.size() > 0);
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

}
