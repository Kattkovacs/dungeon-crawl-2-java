package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class Booster extends Item{
    private int health;

    public Booster(Cell cell) {
        super(cell);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void useBooster(Player player) {
        player.incrementHealth(getHealth()+player.getHealth());
    }
}
