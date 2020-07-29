package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.CellModel;
import com.codecool.dungeoncrawl.model.ItemsModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoJdbc implements ItemsDao {
    private final DataSource dataSource;

    public ItemsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ItemsModel items) {


    }

    @Override
    public void addAll(List<ItemsModel> items) {
        try (Connection conn = dataSource.getConnection()) {
            for (ItemsModel itemModel : items) {
                String sql = "INSERT INTO items (state_id, name, count) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, itemModel.getStateId());
                statement.setString(2, itemModel.getName());
                statement.setInt(3, itemModel.getCount());
                statement.executeUpdate();
                //Read answer from DataBase
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                itemModel.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ItemsModel items) {

    }

    @Override
    public List<ItemsModel> get(int stateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM items WHERE state_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, stateId);
            ResultSet resultSet = statement.executeQuery();
            List<ItemsModel> itemsModels = new ArrayList<>();

            while (resultSet.next()) {
                ItemsModel itemsModel = new ItemsModel();
                itemsModel.setStateId(resultSet.getInt(2));
                itemsModel.setName(resultSet.getString(3));
                itemsModel.setCount(resultSet.getInt(4));
                itemsModels.add(itemsModel);
            }
            return itemsModels;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id: " + e);
        }
    }

    @Override
    public List<ItemsModel> getAll() {
        return null;
    }
}
