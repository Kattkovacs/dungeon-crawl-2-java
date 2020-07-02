package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

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
