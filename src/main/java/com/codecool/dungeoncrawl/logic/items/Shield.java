package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Equipment {
    public Shield(Cell cell) {
        super(cell);
        this.setHealth(4);
        this.setStrength(1);
    }

    @Override
    public String getTileName() {
        return "shield";
    }


}
