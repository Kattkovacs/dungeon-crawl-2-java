package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateDaoJdbc implements GameStateDao {

    private final DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state, int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, player_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, state.getCurrentMap());
            statement.setInt(2, playerId);
            statement.executeUpdate();
            //Read answer from DataBase
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {

    }

    @Override
    public GameState get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            GameState gameState = new GameState(resultSet.getInt(2), resultSet.getInt(4));
            gameState.setId(id);
            gameState.setSavedAt(resultSet.getTimestamp(3));
            return gameState;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading game state with id: " + id, e);
        }
    }

    @Override
    public List<GameState> getAll() {
        List<GameState> gameStateList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GameState gameState = new GameState(resultSet.getInt(2), resultSet.getInt(4));
                gameStateList.add(gameState);
                gameState.setId(resultSet.getInt(1));
                gameState.setSavedAt(resultSet.getTimestamp(3));
            }
            return gameStateList;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading game states", e);
        }
    }
}
