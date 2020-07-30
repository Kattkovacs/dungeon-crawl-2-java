package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.actorsimplementations.Player;

public class ItemsModel extends BaseModel{
    private int stateId;
    private String name;
    private int count;

    public ItemsModel(int stateId, String name, int count) {
        this.stateId = stateId;
        this.name = name;
        this.count = count;
    }

    public ItemsModel() {
    }

    public ItemsModel(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public int getStateId() { return stateId; }

    public void setStateId(int stateId) { this.stateId = stateId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}
