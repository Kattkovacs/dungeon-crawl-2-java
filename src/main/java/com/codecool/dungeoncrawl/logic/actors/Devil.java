package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Devil extends AIPatrol {
    public Devil(Cell cell) {
        super(cell);
    }

    @Override
    protected int setMaxStep() {
        return 3;
    }

    @Override
    public int setBaseHealth() {
        return 10;
    }

    @Override
    public int setBaseAttack() {
        return 5;
    }

    @Override
    public int setBaseStr() {
        return 6;
    }

    @Override
    public int setBaseDex() {
        return 6;
    }

    @Override
    public String getTileName() {
        return "devil";
    }


}
