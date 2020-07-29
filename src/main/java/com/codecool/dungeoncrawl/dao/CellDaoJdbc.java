package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.CellModel;

import javax.sql.DataSource;
import java.util.List;

public class CellDaoJdbc implements CellDao{
    private final DataSource dataSource;

    public CellDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(CellModel cell) {

    }

    @Override
    public void update(CellModel cell) {

    }

    @Override
    public CellModel get(int id) {
        return null;
    }

    @Override
    public List<CellModel> getAll() {
        return null;
    }
}
