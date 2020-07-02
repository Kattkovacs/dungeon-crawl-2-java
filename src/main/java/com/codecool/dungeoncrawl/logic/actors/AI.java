package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.util.RandomUtil;

public abstract class AI extends Actor {
    public AI(Cell cell) {
        super(cell);
    }

    @Override
    public void die() {
        dropLoot();
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

    protected void dropLoot() {
        int randomFactor = RandomUtil.nextInt(20);
        Cell targetCell = this.getCell();
        if (randomFactor == 0) new Armor(targetCell);
        if (randomFactor == 1) new Bread(targetCell);
        if (randomFactor == 2) new Cheese(targetCell);
        if (randomFactor == 3) new Meat(targetCell);
        if (randomFactor == 4) new Ring(targetCell);
        if (randomFactor == 5) new Shield(targetCell);
        if (randomFactor == 6) new Sword(targetCell);
    }
}
