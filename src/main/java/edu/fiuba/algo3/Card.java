package edu.fiuba.algo3;

import java.util.List;

abstract public class Card implements SpecialEffectApplicable {
    protected String name;
    protected String description;


    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void play(Player owner, Player opponent, RowType selectedRowType);

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public abstract List<RowType> getPlayableRowTypes();
}
