package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;

import java.util.List;

public interface ItemsDao {

    void add(ItemsModel items, int gameStateId);
    void update(ItemsModel items);
    ItemsModel get(int id);
    List<ItemsModel> getAll();

}
