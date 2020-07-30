package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Booster;

public class Bread extends Booster {

    public Bread(Cell cell) {
        super(cell);
        this.setHealth(6);
    }

    @Override
    public String getTileName() {
        return "bread";
    }
}
