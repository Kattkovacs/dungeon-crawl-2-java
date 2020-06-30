package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bread extends Booster{

    public Bread(Cell cell) {
        super(cell);
        this.setHealth(10);
    }

    @Override
    public String getTileName() {
        return "bread";
    }
}
