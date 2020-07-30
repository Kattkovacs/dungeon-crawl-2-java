package com.codecool.dungeoncrawl.logic.items.itemsimplementations;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.itemstype.Usable;

public class Key extends Usable {
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
