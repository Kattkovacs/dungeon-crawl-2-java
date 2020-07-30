package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.Main;
import javafx.scene.paint.Color;

public class Log {
    int MAX_VISIBILITY = 3;
    String message;
    int visibleForPeriod;
    Color color;

    public Log(String message, Color color) {
        this.color = color;
        this.message = message;
        visibleForPeriod = MAX_VISIBILITY;
    }

    public void decreaseVisiblePeriod() {
        this.visibleForPeriod--;
        if (this.visibleForPeriod <= 0) Main.logs.remove(this);
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }
}
