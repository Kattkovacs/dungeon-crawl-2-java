package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Equipment;

public class Ring extends Equipment {
    public Ring(Cell cell) {
        super(cell);
        this.setDex(2);
    }

    @Override
    public String getTileName() {
        return "ring";
    }


}
