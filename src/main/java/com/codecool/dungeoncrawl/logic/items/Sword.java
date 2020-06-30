package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Equipment {
    public Sword(Cell cell) {
        super(cell);
        this.setStrength(10);
    }

    @Override
    public String getTileName() {
        return "sword";
    }


}