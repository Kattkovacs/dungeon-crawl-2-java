package com.codecool.dungeoncrawl.dao;


import com.codecool.dungeoncrawl.model.CellModel;

import java.util.List;

public interface CellDao {
    void add(CellModel cell, int gameStateId);
    void update(CellModel cell);
    CellModel get(int id);
    List<CellModel> getAll();
}
