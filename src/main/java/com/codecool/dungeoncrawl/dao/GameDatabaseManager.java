package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private CellDao cellDao;
    private ItemsDao itemsDao;
    private MapDao mapDao;


    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        cellDao = new CellDaoJdbc(dataSource);
        itemsDao = new ItemsDaoJdbc(dataSource);
        mapDao = new MapDaoJdbc(dataSource);
    }


    public void saveGameState(GameMap map) {
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        playerDao.add(playerModel);
        System.out.println("Player saved successfully");

        GameState gameStateModel = new GameState(map.getPlayer().getMapLevel(), playerModel.getId());
        gameStateDao.add(gameStateModel, playerModel.getId());
        System.out.println("State saved successfully");

        List<CellModel> cells = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                String actorName = cell.getActor() != null ? cell.getActor().getTileName() : null;
                String itemName = cell.getItem() != null ? cell.getItem().getTileName() : null;
                CellModel cellModel = new CellModel(gameStateModel.getId(), x, y, actorName,
                        itemName, cell.getType().getDefaultTileName());
                cells.add(cellModel);
            }
        }
        cellDao.addAll(cells);
        System.out.println("Cells saved successfully");

        List<ItemsModel> items = new ArrayList<>();
        Map<Item, Integer> equipments = map.getPlayer().getEquipments();
        Map<Item, Integer> usables = map.getPlayer().getUsables();

        for (Item item : equipments.keySet()) {
            items.add(new ItemsModel(gameStateModel.getId(), item.getTileName(), equipments.get(item)));
        }

        for (Item item : usables.keySet()) {
            items.add(new ItemsModel(gameStateModel.getId(), item.getTileName(), usables.get(item)));
        }
        itemsDao.addAll(items);
        System.out.println("Items saved successfully");

        MapModel mapModel = new MapModel(map.getWidth(), map.getHeight(), map.getStyle(), gameStateModel.getId());
        mapDao.add(mapModel, map.getPlayer().getMapLevel());
        System.out.println("Map saved successfully");
    }

    public GameState getGameState(int id) {
        return gameStateDao.get(id);
    }

    public List<GameState> getGameStateList() {
        return gameStateDao.getAll();
    }

    public MapModel getMapModel(int stateId) {
        return mapDao.get(stateId);
    }

    public List<CellModel> getCellModels(int stateId) {
        return cellDao.get(stateId);
    }

    public PlayerModel getPlayerModel(int id) {
        return playerDao.get(id);
    }

    public List<ItemsModel> getItemsModels(int stateId) {
        return itemsDao.get(stateId);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
