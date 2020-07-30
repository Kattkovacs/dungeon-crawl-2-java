package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import java.util.List;
import java.util.Map;

public interface GameStateDao {
    void add(GameState state, int playerId);
    void update(GameState state);
    GameState get(int id);
    List<GameState> getAll();
}
