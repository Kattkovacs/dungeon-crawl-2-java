package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;

import javax.sql.DataSource;
import java.util.List;

public class ItemsDaoJdbc implements ItemsDao {
    private final DataSource dataSource;

    public ItemsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ItemsModel items, int gameStateId) {

    }

    @Override
    public void update(ItemsModel items) {

    }

    @Override
    public ItemsModel get(int id) {
        return null;
    }

    @Override
    public List<ItemsModel> getAll() {
        return null;
    }
}
