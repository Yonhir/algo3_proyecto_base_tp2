package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Weather extends Special {
    public Weather(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    public abstract void apply(Card card, Row row);
}
