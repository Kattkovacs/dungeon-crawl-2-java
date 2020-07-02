package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    private static int level = 1;

    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream(String.format("/map%s.txt", level));
        level++;
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int style = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        map.setStyle(style);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case '%':
                            cell.setType(CellType.DECOR);
                            break;
                        case '|':
                            cell.setType(CellType.COLUMN);
                            break;
                        case '+':
                            cell.setType(CellType.EXIT);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Bread(cell);
                            break;
                        case 'G':
                            cell.setType(CellType.GRAVE);
                            break;
                        case 'c':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Goliath(cell));
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Ghost(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Pichatsu(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Bat(cell));
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Mage(cell));
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Devil(cell));
                            break;
                        case 'A':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell);
                            break;
                        case 'C':
                            cell.setType(CellType.FLOOR);
                            new Cheese(cell);
                            break;
                        case 'M':
                            cell.setType(CellType.FLOOR);
                            new Meat(cell);
                            break;
                        case 'R':
                            cell.setType(CellType.FLOOR);
                            new Ring(cell);
                            break;
                        case 'H':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
