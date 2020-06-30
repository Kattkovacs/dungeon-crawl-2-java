package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    COLUMN("column"),
    DECOR("decor"),
    EXIT("exit");

    private final String tileName;
    private int style;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName + getStyle();
    }

    public int getStyle() { return style; }

    public void setStyle(int style) { this.style = style; }
}
