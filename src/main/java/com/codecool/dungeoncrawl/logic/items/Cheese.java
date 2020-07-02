package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cheese extends Booster{

    public Cheese(Cell cell) {
        super(cell);
        this.setHealth(15);
    }

    @Override
    public String getTileName() {
        return "cheese";
    }
}
