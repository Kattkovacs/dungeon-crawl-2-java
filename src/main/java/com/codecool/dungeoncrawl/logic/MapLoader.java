package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.actorsimplementations.*;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.items.itemsimplementations.*;
import com.codecool.dungeoncrawl.model.*;
import com.codecool.dungeoncrawl.util.Log;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MapLoader {


    public static GameMap loadMap(int level, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream(String.format("/map%s.txt", level));
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
                    cellFactory(x, y, map, String.valueOf(line.charAt(x)), player);
                }
            }
        }
        return map;
    }

    public static GameMap loadSavedMap(GameState gameState,
                                       MapModel mapModel,
                                       List<CellModel> cellModels,
                                       PlayerModel playerModel,
                                       List<ItemsModel> itemsModels) {

        GameMap map = new GameMap(mapModel.getWidth(), mapModel.getHeight(), CellType.EMPTY);
        map.setStyle(mapModel.getStyle());

        updateCells(cellModels, map);

        Player player = map.getPlayer();
        player.setMapLevel(gameState.getCurrentMap());
        player.setHealth(playerModel.getHp());
        player.setName(playerModel.getPlayerName());
        for (ItemsModel itemsModel: itemsModels) {
            for (int i = 0; i < itemsModel.getCount(); i++) {
                player.loadItem(Item.itemFactory(itemsModel.getName(), player.getCell()));
            }
        }
        Main.logs.add(new Log("Game loaded successfully", Color.GREEN));
        return map;
    }

    private static void updateCells(List<CellModel> cellModels, GameMap map) {
        for(CellModel cellModel : cellModels) {
            String actorName = cellModel.getActor();
            String itemName = cellModel.getItem();

            if (actorName != null) {
                cellFactory(cellModel.getX(), cellModel.getY(), map, actorName, null);
            }
            if (itemName != null) {
                cellFactory(cellModel.getX(), cellModel.getY(), map, itemName, null);
            }
            cellFactory(cellModel.getX(), cellModel.getY(), map, cellModel.getCellType(), null);
        }
    }

    public static void cellFactory(int x, int y, GameMap map, String cellElement, Player player) {
        Cell cell = map.getCell(x, y);
        switch (cellElement) {
            case "empty":
            case " ":
                cell.setType(CellType.EMPTY);
                break;
            case "wall":
            case "#":
                cell.setType(CellType.WALL);
                break;
            case "floor":
            case ".":
                cell.setType(CellType.FLOOR);
                break;
            case "decor":
            case "%":
                cell.setType(CellType.DECOR);
                break;
            case "column":
            case "|":
                cell.setType(CellType.COLUMN);
                break;
            case "exit":
            case "+":
                cell.setType(CellType.EXIT);
                break;
            case "skeleton":
            case "s":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Skeleton(cell));
                break;
            case "player":
            case "@":
                cell.setType(CellType.FLOOR);
                if (player != null) {
                    player.setCell(cell);
                    map.setPlayer(player);
                    cell.setActor(player);
                } else {
                    map.setPlayer(new Player(cell));
                }
                break;
            case "sword":
            case "S":
                cell.setType(CellType.FLOOR);
                new Sword(cell);
                break;
            case "key":
            case "K":
                cell.setType(CellType.FLOOR);
                new Key(cell);
                break;
            case "bread":
            case "B":
                cell.setType(CellType.FLOOR);
                new Bread(cell);
                break;
            case "grave":
            case "G":
                cell.setType(CellType.GRAVE);
                break;
            case "closedDoor":
            case "c":
                cell.setType(CellType.CLOSED_DOOR);
                break;
            case "openDoor":
                cell.setType(CellType.OPEN_DOOR);
                break;
            case "goliath":
            case "g":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Goliath(cell));
                break;
            case "ghost":
            case "h":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Ghost(cell));
                break;
            case "pichatsu":
            case "p":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Pichatsu(cell));
                break;
            case "bat":
            case "b":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Bat(cell));
                break;
            case "mage":
            case "m":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Mage(cell));
                break;
            case "devil":
            case "d":
                cell.setType(CellType.FLOOR);
                map.addEnemy(new Devil(cell));
                break;
            case "armor":
            case "A":
                cell.setType(CellType.FLOOR);
                new Armor(cell);
                break;
            case "cheese":
            case "C":
                cell.setType(CellType.FLOOR);
                new Cheese(cell);
                break;
            case "meat":
            case "M":
                cell.setType(CellType.FLOOR);
                new Meat(cell);
                break;
            case "ring":
            case "R":
                cell.setType(CellType.FLOOR);
                new Ring(cell);
                break;
            case "shield":
            case "H":
                cell.setType(CellType.FLOOR);
                new Shield(cell);
                break;
            case "startPoint":
            case "0":
                cell.setType(CellType.START_POINT);
                break;
            default:
                throw new RuntimeException("Unrecognized character: '" + cellElement + "'");
        }
    }
}
