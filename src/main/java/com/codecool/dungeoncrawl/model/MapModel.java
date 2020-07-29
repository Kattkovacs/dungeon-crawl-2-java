package com.codecool.dungeoncrawl.model;

public class MapModel extends BaseModel{

    private int width;
    private int height;
    private int style;
    private int stateId;

    public MapModel(int width, int height, int style, int stateId) {
        this.width = width;
        this.height = height;
        this.style = style;
        this.stateId = stateId;
    }

    public int getWidth() { return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getStyle() { return style; }

    public void setStyle(int style) { this.style = style; }

    public int getStateId() { return stateId; }

    public void setStateId(int stateId) { this.stateId = stateId; }
}
