package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.*;

public class Player extends Actor {
    private Map<Equipment, Integer> equipments = new LinkedHashMap<>();
    private Map<Usable, Integer> usables = new LinkedHashMap<>();
    private Cell actionCell;

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
        if (nextCell.canMoveThrough() && nextCell.getActor() == null) {
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        } else if (nextCell.getActor() instanceof AI) {
            fight(nextCell.getActor());
        }
        if (nextCell.getType().equals(CellType.CLOSED_DOOR)) {
            actionCell = nextCell;
        } else {
            actionCell = null;
        }
    }

    @Override
    public void die() {
        getCell().setType(CellType.GRAVE);
        getCell().setActor(null);
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

    public void useItem() {
        if (actionCell != null) {
            Key key = getKey();
            if (key != null) {
                actionCell.setType(CellType.OPEN_DOOR);
                usables.remove(key);
            }
        }
    }

    public Key getKey() {
        for (Usable key : usables.keySet()) {
            if (key instanceof Key) {
                return (Key) key;
            }
        }
        return null;
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

    public String collectActions(){
        StringBuilder actionStr = new StringBuilder();
        Item item = getCell().getItem();
        if (item != null && item instanceof Booster) {
            actionStr.append(String.format("Press TAB to use %s", item.getTileName()));
        } else if (item != null) {
            actionStr.append(String.format("Press TAB to collect %s to inventory", item.getTileName()));
        }
        if (actionCell != null && actionCell.getType().equals(CellType.CLOSED_DOOR)) {
            Key key = getKey();
            if (key != null) {
                actionStr.append("Press TAB to open the door");
            } else {
                actionStr.append("You need a key to open the door");
            }
        }
        return actionStr.toString();
    }

    public String collectEnemyInfo(){
        StringBuilder enemyStr = new StringBuilder();
        Actor enemy;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cell cell = getCell().getNeighbor(i, j);
                if (cell.getActor() instanceof AI){
                    enemy = cell.getActor();
                    enemyStr.append(String.format("Enemy: %s \n",enemy.getTileName()));
                    enemyStr.append(String.format("Health: %s / %s \n",enemy.getHealth(), enemy.getBaseHealth()));
                    enemyStr.append(String.format("Attack: %s \n",enemy.getAttack()));
                }
            }
        }
        return enemyStr.toString();
    }
}

