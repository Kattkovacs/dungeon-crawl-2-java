package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.HashMap;

public abstract class Item implements Drawable {
    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void clearCell(){
        cell.setItem(null);
    }

    @Override
    public String toString() {
        return this.getTileName();
    }

    public static Item itemFactory(String itemName, Cell cell) {
        switch (itemName) {
            case "armor":
                return new Armor(cell);
            case "key":
                return new Key(cell);
            case "ring":
                return new Ring(cell);
            case "shield":
                return new Shield(cell);
            case "sword":
                return new Sword(cell);
            default:
                throw new RuntimeException("Unknown item!" + itemName);
        }
    }

}
