package com.codecool.dungeoncrawl.dao.interfaces;


import com.codecool.dungeoncrawl.model.CellModel;

import java.util.List;

public interface CellDao {
    void add(CellModel cell);
    void addAll(List<CellModel> cells);
    void update(CellModel cell);
    List<CellModel> get(int state_id);
    List<CellModel> getAll();
}
