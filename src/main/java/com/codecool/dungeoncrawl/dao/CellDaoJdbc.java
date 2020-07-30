package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.CellModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CellDaoJdbc implements CellDao{
    private final DataSource dataSource;

    public CellDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(CellModel cell) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cell (state_id, x, y, actor, item, cell_type) VALUES (?, ?, ?, ?, ?, ?)";
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAll(List<CellModel> cells) {
        try (Connection conn = dataSource.getConnection()) {
            for (CellModel cellModel : cells) {
                String sql = "INSERT INTO cell (state_id, x, y, actor, item, cell_type) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, cellModel.getStateId());
                statement.setInt(2, cellModel.getX());
                statement.setInt(3, cellModel.getY());
                statement.setString(4, cellModel.getActor());
                statement.setString(5, cellModel.getItem());
                statement.setString(6, cellModel.getCellType());
                statement.executeUpdate();
                //Read answer from DataBase
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                cellModel.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(CellModel cell) {

    }

    @Override
    public List<CellModel> get(int state_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM cell WHERE state_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, state_id);
            ResultSet resultSet = statement.executeQuery();
            List<CellModel> cellModels = new ArrayList<>();

            while (resultSet.next()) {
                CellModel cellModel = new CellModel(state_id, resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7));
                cellModels.add(cellModel);
            }
            return cellModels;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id: " + e);
        }
    }

    @Override
    public List<CellModel> getAll() {
        return null;
    }
}
