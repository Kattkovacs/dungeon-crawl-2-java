package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;

import java.util.List;

public interface ItemsDao {

    void add(ItemsModel items);
    void addAll(List<ItemsModel> items);
    void update(ItemsModel items);
    ItemsModel get(int id);
    List<ItemsModel> getAll();

}
