package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Goliath extends AIStandStill {
    public Goliath(Cell cell) {
        super(cell);
    }

    @Override
    public int setBaseHealth() {
        return 16;
    }

    @Override
    public int setBaseAttack() {
        return 3;
    }

    @Override
    public int setBaseStr() {
        return 4;
    }

    @Override
    public int setBaseDex() {
        return 1;
    }

    @Override
    public String getTileName() {
        return "goliath";
    }


}
