package com.codecool.dungeoncrawl.dao.interfaces;

import com.codecool.dungeoncrawl.model.ItemsModel;

import java.util.List;

public interface ItemsDao {

    void add(ItemsModel items);
    void addAll(List<ItemsModel> items);
    void update(ItemsModel items);
    List<ItemsModel> get(int stateId);
    List<ItemsModel> getAll();

}
