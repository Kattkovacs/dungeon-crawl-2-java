package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MapModel;

import java.util.List;

public interface MapDao {

    void add(MapModel map, int gameStateId);
    void update(MapModel map);
    MapModel get(int id);
    List<MapModel> getAll();

}
