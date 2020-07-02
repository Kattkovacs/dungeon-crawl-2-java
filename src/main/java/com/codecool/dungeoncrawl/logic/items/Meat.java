package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Meat extends Booster{

    public Meat(Cell cell) {
        super(cell);
        this.setHealth(30);
    }

    @Override
    public String getTileName() {
        return "meat";
    }
}
