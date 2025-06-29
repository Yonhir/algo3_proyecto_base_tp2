package edu.fiuba.algo3.models.cards.specials;

import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.types.SpecialType;

import java.util.List;

public class ScorchCard extends Special {
    
    public ScorchCard(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        // Scorch eliminates the strongest cards on the battlefield
        // This would need to be implemented based on the game logic
    }
} 