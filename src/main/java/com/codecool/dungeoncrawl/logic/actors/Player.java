package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Booster;
import com.codecool.dungeoncrawl.logic.items.Equipment;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Usable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private List<Equipment> equipments = new LinkedList<>();
    private List<Usable> usables = new LinkedList<>();

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) {
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

    public void pickUpItem() {
        Item item = getCell().getItem();
        if (item != null) {
            if (item instanceof Equipment) {
                equipments.add((Equipment) item);
            } else if (item instanceof Usable) {
                usables.add((Usable) item);
            } else if (item instanceof Booster) {
                ((Booster) item).useBooster(this);
            }
            item.clearCell();
        }

    }

    public String getTileName() {
        return "player";
    }

}

