package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Equipment extends Item{
    private int attack;
    private int health;
    private int strength;
    private int dex;

    public Equipment(Cell cell) {
        super(cell);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void useEquipment(){

    }

    public int getDex() { return dex; }

    public void setDex(int dex) {
        this.dex = dex;
    }
}
