package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Booster;

public class Cheese extends Booster {

    public Cheese(Cell cell) {
        super(cell);
        this.setHealth(15);
    }

    @Override
    public String getTileName() {
        return "cheese";
    }
}
