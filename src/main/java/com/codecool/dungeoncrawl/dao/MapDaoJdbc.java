package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MapModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MapDaoJdbc implements MapDao{
    private final DataSource dataSource;

    public MapDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(MapModel map, int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO map (state_id, width, height, style) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, map.getStateId());
            statement.setInt(2, map.getWidth());
            statement.setInt(3, map.getHeight());
            statement.setInt(4, map.getStyle());
            statement.executeUpdate();
            //Read answer from DataBase
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            map.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MapModel map) {

    }

    @Override
    public MapModel get(int id) {
        return null;
    }

    @Override
    public List<MapModel> getAll() {
        return null;
    }
}
