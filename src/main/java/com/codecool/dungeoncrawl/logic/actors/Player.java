package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Booster;
import com.codecool.dungeoncrawl.logic.items.Equipment;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Usable;

import java.util.*;

public class Player extends Actor {
    private Map<Equipment, Integer> equipments = new LinkedHashMap<>();
    private Map<Usable, Integer> usables = new LinkedHashMap<>();

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public int setBaseHealth() {
        return 10;
    }

    @Override
    public int setBaseAttack() {
        return 4;
    }

    @Override
    public int setBaseStr() {
        return 5;
    }

    @Override
    public int setBaseDex() {
        return 5;
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

    @Override
    public void die() {
        // TODO
    }

    public void pickUpItem() {
        Item item = getCell().getItem();
        if (item != null) {
            if (item instanceof Equipment) {
                addToInventory(equipments, item);
            } else if (item instanceof Usable) {
                addToInventory(usables, item);
            } else if (item instanceof Booster) {
                ((Booster) item).useBooster(this);
            }
            item.clearCell();
        }
    }

    public <K> void addToInventory(Map<K, Integer> inventory, Item item){
        K key = (K) item;
        if (inventory.containsKey(key)) {
            inventory.replace (key, inventory.get(key) + 1);
        } else {
            inventory.putIfAbsent(key, 1);
        }
    }

    public String getTileName() {
        return "player";
    }

    public Map<Equipment, Integer> getEquipments() {
        return equipments;
    }

    public Map<Usable, Integer> getUsables() {
        return usables;
    }

    public <K> String getInventoryItem(Map<K, Integer> inventory) {
        StringBuilder sb = new StringBuilder();
        for (K key : inventory.keySet()) {
            sb.append(key).append(": ").append(inventory.get(key)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int getAttack() {
        return super.getAttack();
    }

    @Override
    public int getStr() {
        return super.getStr();
    }

    @Override
    public int getDex() {
        return super.getDex();
    }
}

