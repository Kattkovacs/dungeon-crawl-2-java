package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.util.Log;
import com.codecool.dungeoncrawl.util.RandomUtil;
import javafx.scene.paint.Color;

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
        int max = enemy.getDex() > this.getDex() ? enemy.getDex() + enemy.getDex() / 4 : this.getDex() + enemy.getDex() / 4;
        boolean hit = RandomUtil.nextInt(max) <= this.getDex();
        boolean critical = RandomUtil.nextInt(max) * 2 <= this.getDex() / 2;
        String message;
        if (hit) {
            int damage = this.getAttack() + this.getStr() / enemy.getStr();
            if (critical) damage = damage * 2;
            message = "" + this.getTileName() + " cause " + damage +
                    (critical ? " CRITICAL dmg" : " dmg");
            enemy.decreaseHealth(damage);
            Main.logs.add(new Log(message, Color.DARKORANGE));
        } else {
            message = "" + this.getTileName() + " miss!";
            Main.logs.add(new Log(message, Color.YELLOW));
        }

    }

    public void fight(Actor enemy) {
        attackEnemy(enemy);
        if (enemy.getHealth() > 0) {
            enemy.attackEnemy(this);
        }
    }

    public void resetHealth() {
        this.health = getBaseHealth();
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

    public void setHealth(int health) {
        this.health = health;
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
