package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends AIRandomMove {
    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public int setBaseHealth() {
        return 6;
    }

    @Override
    public int setBaseAttack() {
        return 3;
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
        return "ghost";
    }


}
