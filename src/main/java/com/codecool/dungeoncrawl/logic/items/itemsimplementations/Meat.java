package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Booster;

public class Meat extends Booster {

    public Meat(Cell cell) {
        super(cell);
        this.setHealth(30);
    }

    @Override
    public String getTileName() {
        return "meat";
    }
}
