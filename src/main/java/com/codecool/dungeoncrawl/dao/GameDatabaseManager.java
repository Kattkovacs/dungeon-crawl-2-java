package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.CellModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

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

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                String actorName = cell.getActor() != null ? cell.getActor().getTileName() : null;
                String itemName = cell.getItem() != null ? cell.getItem().getTileName() : null;
                CellModel cellModel = new CellModel(gameStateModel.getId(), x, y, actorName,
                        itemName, cell.getType().getDefaultTileName());
                cellDao.add(cellModel, cellModel.getStateId());
            }
        }
        System.out.println("Tiles saved successfully");
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
