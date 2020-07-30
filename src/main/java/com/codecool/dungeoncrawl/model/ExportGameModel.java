package com.codecool.dungeoncrawl.model;

import java.util.List;

public class ExportGameModel {
    GameState gameState;
    PlayerModel playerModel;
    MapModel mapModel;
    List<CellModel> cellModel;
    List<ItemsModel> itemsModel;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public List<CellModel> getCellModel() {
        return cellModel;
    }

    public void setCellModel(List<CellModel> cellModel) {
        this.cellModel = cellModel;
    }

    public List<ItemsModel> getItemsModel() {
        return itemsModel;
    }

    public void setItemsModel(List<ItemsModel> itemsModel) {
        this.itemsModel = itemsModel;
    }
}
