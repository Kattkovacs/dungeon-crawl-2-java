package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends AIRandomMove {
    public Bat(Cell cell) {
        super(cell);
    }

    @Override
    public int setBaseHealth() {
        return 8;
    }

    @Override
    public int setBaseAttack() {
        return 4;
    }

    @Override
    public int setBaseStr() {
        return 4;
    }

    @Override
    public int setBaseDex() {
        return 8;
    }

    @Override
    public String getTileName() {
        return "bat";
    }


}
