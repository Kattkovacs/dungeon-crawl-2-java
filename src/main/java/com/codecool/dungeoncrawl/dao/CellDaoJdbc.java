package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.CellModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class CellDaoJdbc implements CellDao{
    private final DataSource dataSource;

    public CellDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(CellModel cell, int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (state_id, x, y, actor, item, cell_type) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cell.getStateId());
            statement.setInt(2, cell.getX());
            statement.setInt(3, cell.getY());
            statement.setString(4, cell.getActor());
            statement.setString(5, cell.getItem());
            statement.setString(6, cell.getCellType());
            statement.executeUpdate();
            //Read answer from DataBase
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            cell.setId(resultSet.getInt(1));
            System.out.println("x:" + cell.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CellModel cell) {

    }

    @Override
    public CellModel get(int id) {
        return null;
    }

    @Override
    public List<CellModel> getAll() {
        return null;
    }
}
