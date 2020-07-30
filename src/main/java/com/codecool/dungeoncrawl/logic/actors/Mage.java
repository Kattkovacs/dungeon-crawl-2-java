package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mage extends AIGhostMove {
    public Mage(Cell cell) {
        super(cell);
    }

    @Override
    protected int setRange() {
        return 4;
    }

    @Override
    public int setBaseHealth() {
        return 20;
    }

    @Override
    public int setBaseAttack() {
        return 6;
    }

    @Override
    public int setBaseStr() {
        return 2;
    }

    @Override
    public int setBaseDex() {
        return 2;
    }

    @Override
    public String getTileName() {
        return "mage";
    }


}
