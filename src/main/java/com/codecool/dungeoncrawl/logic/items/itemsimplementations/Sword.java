package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Equipment;

public class Sword extends Equipment {
    public Sword(Cell cell) {
        super(cell);
        this.setAttack(2);
    }

    @Override
    public String getTileName() {
        return "sword";
    }


}
