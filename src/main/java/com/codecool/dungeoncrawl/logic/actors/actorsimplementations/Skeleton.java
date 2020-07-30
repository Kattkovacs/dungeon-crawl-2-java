package com.codecool.dungeoncrawl.logic.actors.actorsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.aimove.AIRandomMove;

public class Skeleton extends AIRandomMove {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public int setBaseHealth() {
        return 10;
    }

    @Override
    public int setBaseAttack() {
        return 2;
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
        return "skeleton";
    }

}
