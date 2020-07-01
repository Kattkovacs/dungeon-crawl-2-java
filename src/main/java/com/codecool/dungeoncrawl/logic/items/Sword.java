package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

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
