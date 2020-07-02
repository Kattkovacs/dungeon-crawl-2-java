package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.RandomUtil;

public abstract class AIGhostMove extends AI {

    private int maxRange;

    public AIGhostMove(Cell cell) {
        super(cell);
        maxRange = setRange();
    }

    @Override
    public void move(int dx, int dy) {
        // lista az őt körülvevő lehetséges cellákról
        // cella a pályán van?
        // megfelel nekünk?
        // cella hozzaadása a listához

        Actor player = getPlayerNearby();
        if (player != null) {
            fight(player);
        } else {
            Cell nextCell = getCell();

            do {
                int x = RandomUtil.nextInt(maxRange * 2 + 1) - maxRange; //3*2=6, 6+1=7 -> 0->6 -3 = -3...3
                int y = RandomUtil.nextInt(maxRange * 2 + 1) - maxRange;
                if (getCell().getGameMap().isOnMap(x, y)) {
                    nextCell = getCell().getNeighbor(x, y);
                }
            } while (!nextCell.canMoveThrough() || nextCell.getActor() != null);

            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

    protected abstract int setRange();


}
