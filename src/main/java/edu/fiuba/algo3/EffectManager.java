package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class EffectManager {
    private final List<PointModifier> modifiers;

    public EffectManager() {
        this.modifiers = new ArrayList<>();
    }

    public void addModifier(PointModifier modifier) {
        modifiers.add(modifier);
    }

    public void removeModifier(PointModifier modifier) {
        modifiers.remove(modifier);
    }

    public int calculateTotalEffect(int basePoints) {
        int currentPoints = basePoints;
        
        // Apply all modifiers in sequence
        for (PointModifier modifier : modifiers) {
            currentPoints = modifier.modifyPoints(currentPoints);
        }
        
        return currentPoints;
    }

    public List<PointModifier> getModifiers() {
        return new ArrayList<>(modifiers);
    }

    public void clearEffects() {
        modifiers.clear();
    }
}
