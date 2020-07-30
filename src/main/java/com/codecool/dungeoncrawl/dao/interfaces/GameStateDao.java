package com.codecool.dungeoncrawl.dao.interfaces;

import com.codecool.dungeoncrawl.model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state, int playerId);
    void update(GameState state);
    GameState get(int id);
    List<GameState> getAll();
}
