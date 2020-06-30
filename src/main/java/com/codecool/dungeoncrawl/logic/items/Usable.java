package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Usable extends Item{
    private String useFor;

    public Usable(Cell cell) {
        super(cell);
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public void useUsable(){

    }
}
