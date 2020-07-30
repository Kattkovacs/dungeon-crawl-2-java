package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
        this.type.setStyle(gameMap.getStyle());
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        int width = getGameMap().getWidth();
        int height = getGameMap().getHeight();

        if (x + dx >= 0 && x + dx <= width-1 && y + dy >= 0 && y + dy <= height-1) {
            return gameMap.getCell(x + dx, y + dy);
        }
        return this;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Item getItem() { return item; }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean canMoveThrough() {
        return this.getType().equals(CellType.FLOOR) ||
                this.getType().equals(CellType.OPEN_DOOR);
    }
}
