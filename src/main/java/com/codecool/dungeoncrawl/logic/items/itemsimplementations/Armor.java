package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Equipment;

public class Armor extends Equipment {
    public Armor(Cell cell) {
        super(cell);
        this.setHealth(5);
    }

    @Override
    public String getTileName() {
        return "armor";
    }


}
