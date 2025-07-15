package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.sections.types.SectionType;

import java.io.InputStream;

public class SectionTypeIconMapper {

    public static InputStream getIconStream(SectionType type) {
        if (type == null) return null;

        String path = null;

        String typeName = type.getClass().getSimpleName();
        switch (typeName) {
            case "CloseCombatType":
                path = "/images/closeCombat.png";
                break;
            case "RangedType":
                path = "/images/ranged.png";
                break;
            case "SiegeType":
                path = "/images/siege.png";
                break;
            default:
                System.err.println("Unknown SectionType: " + typeName);
        }

        if (path == null) return null;

        return SectionTypeIconMapper.class.getResourceAsStream(path);
    }
}

