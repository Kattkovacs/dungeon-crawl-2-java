package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.*;

public class Player extends Actor {
    private String name;
    private final Map<Item, Integer> equipments = new HashMap<>();
    private final Map<Item, Integer> usables = new LinkedHashMap<>();
    private Cell actionCell;
    private boolean nextLevel = false;
    private boolean died = false;
    private int mapLevel = 1;

    public int getMapLevel() { return mapLevel; }

    public void setMapLevel(int mapLevel) { this.mapLevel = mapLevel; }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public boolean isDied() { return died; }

    public void setDied(boolean died) { this.died = died; }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Player(Cell cell) {
        super(cell);
        this.setName("Anonymous");
    }

    public Player(Cell cell, String name) {
        super(cell);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        if (nextCell.getType().equals(CellType.EXIT)) {
            nextLevel = true;
        }
    }

    @Override
    public void die() {
        getCell().setType(CellType.GRAVE);
        getCell().setActor(null);
        setDied(true);
    }

    public void pickUpItem() {
        Item item = getCell().getItem();
        if (item != null) {
            if (item instanceof Booster) {
                ((Booster) item).useBooster(this);
            } else if (item instanceof Equipment) {
                addToInventory(equipments, item);
            } else if (item instanceof Usable) {
                addToInventory(usables, item);
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
        for (Item key : usables.keySet()) {
            if (key instanceof Key) {
                return (Key) key;
            }
        }
        return null;
    }

    public void addToInventory(Map<Item, Integer> inventory, Item item){
        System.out.println();
        Item sameItem = getSameInInventory(item, inventory.keySet());
        if (sameItem != null) {
            inventory.replace(sameItem, inventory.get(sameItem) + 1);
        } else {
            inventory.putIfAbsent(item, 1);
        }
    }

    public Item getSameInInventory(Item newItem, Set<Item> items) {
        for (Item item: items) {
            if (item.getTileName().equals(newItem.getTileName())) return item;
        }
        return null;
    }


    public String getTileName() {
        return "player";
    }

    public Map<Item, Integer> getEquipments() {
        return equipments;
    }

    public Map<Item, Integer> getUsables() {
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
    public int getBaseHealth() {
        int calculatedBaseHealth = super.getBaseHealth();
        for (Item key : equipments.keySet()) {
            Equipment item = (Equipment) key;
            calculatedBaseHealth += item.getHealth();
        }
        return calculatedBaseHealth;
    }

    @Override
    public int getAttack() {
        int calculatedAttack = super.getAttack();
        for (Item key : equipments.keySet()) {
            Equipment item = (Equipment) key;
            calculatedAttack += item.getAttack();
        }
        return calculatedAttack;
    }

    @Override
    public int getStr() {
        int calculatedStr = super.getStr();
        for (Item key : equipments.keySet()) {
            Equipment item = (Equipment) key;
            calculatedStr += item.getStrength();
        }
        return calculatedStr;
    }

    @Override
    public int getDex() {
        int calculatedDex = super.getDex();
        for (Item key : equipments.keySet()) {
            Equipment item = (Equipment) key;
            calculatedDex += item.getDex();
        }
        return calculatedDex;
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
        int enemyCounter = 1;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cell cell = getCell().getNeighbor(i, j);
                if (cell.getActor() instanceof AI){
                    enemy = cell.getActor();
                    enemyStr.append(String.format("[%d]Enemy: %s \n", enemyCounter, enemy.getTileName()));
                    enemyStr.append(String.format("Health: %s / %s \n",enemy.getHealth(), enemy.getBaseHealth()));
                    enemyStr.append(String.format("Attack: %s \n",enemy.getAttack()));
                    enemyStr.append(String.format("--------------------- \n"));
                    enemyCounter += 1;
                }
            }
        }
        return enemyStr.toString();
    }


}

