package com.codecool.dungeoncrawl.model;

public class CellModel extends BaseModel{

    private int stateId;
    private int x;
    private int y;
    private String actor;
    private String item;
    private String cellType;

    public CellModel(int stateId, int x, int y, String actor, String item, String cellType) {
        this.stateId = stateId;
        this.x = x;
        this.y = y;
        this.actor = actor;
        this.item = item;
        this.cellType = cellType;
    }

    public int getStateId() { return stateId; }

    public void setStateId(int stateId) { this.stateId = stateId; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public String getActor() { return actor; }

    public void setActor(String actor) { this.actor = actor; }

    public String getItem() { return item; }

    public String getCellType() { return cellType; }
}
