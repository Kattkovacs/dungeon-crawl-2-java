package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int baseHealth;
    private int health;
    private int baseAttack;
    private int baseStr;
    private int baseDex;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        this.baseHealth = setBaseHealth();
        this.health = baseHealth;
        this.baseAttack = setBaseAttack();
        this.baseStr = setBaseStr();
        this.baseDex = setBaseDex();
    }

    public abstract int setBaseHealth();

    public abstract int setBaseAttack();

    public abstract int setBaseStr();

    public abstract int setBaseDex();

    public abstract void move(int dx, int dy);

    public void attackEnemy(Actor enemy) {
        enemy.decreaseHealth(this.getAttack());
    }

    public void fight(Actor enemy) {
        attackEnemy(enemy);
        if (enemy.getHealth() > 0) {
            enemy.attackEnemy(this);
        }
    }

    public void incrementHealth(int health) {
        this.health += health;
        if (this.health > this.getBaseHealth()) this.health = this.getBaseHealth();
    }

    public void decreaseHealth(int health) {
        this.health -= health;
        if (this.health <= 0) die();
    }

    public abstract void die();

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getAttack() {
        return baseAttack;
    }

    public int getStr() {
        return baseStr;
    }

    public int getDex() {
        return baseDex;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
