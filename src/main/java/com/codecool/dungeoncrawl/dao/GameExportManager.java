package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.actorsimplementations.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.*;
import com.codecool.dungeoncrawl.util.Log;
import com.google.gson.Gson;
import javafx.scene.paint.Color;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameExportManager {
    private final ExportGameModel exportGameModel;
    private GameMap map;

    public GameExportManager(GameMap map) {
        this.map = map;
        exportGameModel = new ExportGameModel();
    }

    public GameMap importGame(File file) {
        GameMap map;
        String serializedGame = loadFromFile(file);
        if (serializedGame != null) {
            ExportGameModel exportGameModel = new Gson().fromJson(serializedGame, ExportGameModel.class);
            map = MapLoader.loadSavedMap(
                    exportGameModel.getGameState(),
                    exportGameModel.getMapModel(),
                    exportGameModel.getCellModel(),
                    exportGameModel.getPlayerModel(),
                    exportGameModel.getItemsModel()
            );
            System.out.println("Game imported successfuly");
            return map;
        }
        return null;
    }

    public void exportGame() {
        createPlayerModel();
        createStateModel();
        createMapModel();
        createCellModel();
        createItemModel();
        String serializedGame = new Gson().toJson(exportGameModel);
        saveToFile(serializedGame);
        System.out.println("Game exported successfuly");
        Main.logs.add(new Log("Game exported successfuly", Color.GREEN));
    }

    private void saveToFile(String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileName = "DC-" + sdf.format(timestamp) + ".exp";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error when writing file! " + e);
        }
    }

    private String loadFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error when writing file! " + e);
        }
        return null;
    }

    private void createPlayerModel() {
        Player player = map.getPlayer();
        exportGameModel.setPlayerModel(new PlayerModel(player));
    }

    private void createStateModel() {
        Player player = map.getPlayer();
        exportGameModel.setGameState(new GameState(player.getMapLevel()));
    }

    private void createMapModel() {
        exportGameModel.setMapModel(new MapModel(map));
    }

    private void createCellModel() {
        List<CellModel> cellModels = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                cellModels.add(new CellModel(cell));
            }
        }
        exportGameModel.setCellModel(cellModels);
    }

    private void createItemModel() {
        List<ItemsModel> itemsModels = new ArrayList<>();

        Map<Item, Integer> equipments = map.getPlayer().getEquipments();
        Map<Item, Integer> usables = map.getPlayer().getUsables();

        for (Item item : equipments.keySet()) {
            itemsModels.add(new ItemsModel(item.getTileName(), equipments.get(item)));
        }

        for (Item item : usables.keySet()) {
            itemsModels.add(new ItemsModel(item.getTileName(), usables.get(item)));
        }
        exportGameModel.setItemsModel(itemsModels);
    }

}
