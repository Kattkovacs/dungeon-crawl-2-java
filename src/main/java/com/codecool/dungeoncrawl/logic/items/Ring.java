package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

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
