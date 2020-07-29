package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private Date savedAt;
    private int currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private int playerId;

    public GameState(int currentMap, int playerId) {
        this.currentMap = currentMap;
        this.playerId = playerId;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() { return discoveredMaps; }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public int getPlayerId() { return playerId; }

    public void setPlayerId(int playerId) { this.playerId = playerId; }
}
