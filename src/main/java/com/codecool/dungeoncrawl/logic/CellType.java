package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    COLUMN("column"),
    DECOR("decor"),
    EXIT("exit"),
    GRAVE("grave"),
    OPEN_DOOR("openDoor"),
    CLOSED_DOOR("closedDoor"),
    START_POINT("startPoint");

    private final String tileName;
    private int style;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        if (this.getDefaultTileName().equals("decor")) {
            return tileName + getStyle() + (Main.switcher ? "a" : "b");
        }
        return tileName + getStyle();
    }

    public String getDefaultTileName() {
        return tileName;
    }

    public int getStyle() { return style; }

    public void setStyle(int style) { this.style = style; }
}
