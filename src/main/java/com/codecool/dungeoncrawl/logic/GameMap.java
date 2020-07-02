package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.LinkedList;
import java.util.List;


public class GameMap {
    private int width;
    private int height;
    private int style;
    private Cell[][] cells;
    private Player player;
    private List<Actor> enemies;


    public GameMap(int width, int height, CellType defaultCellType) {
        enemies = new LinkedList<>();
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public boolean isOnMap(int x, int y){
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    private void removeDeadEnemy() {
        while (true) {
            boolean remove = false;
            for (Actor enemy : getEnemies()) {
                if (enemy.getHealth() <= 0) {
                    enemies.remove(enemy);
                    remove = true;
                    break;
                }
            }
            if (!remove) {
                break;
            }
        }
    }

    public void moveAI() {
        removeDeadEnemy();
        for (Actor enemy : getEnemies()) {
            enemy.move(0, 0);
        }
    }

    public void addEnemy(Actor enemy) {
        enemies.add(enemy);
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Actor> getEnemies() {
        return enemies;
    }

}
