package edu.fiuba.algo3.modelo.cards;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Color;
import edu.fiuba.algo3.modelo.Player;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.units.CommonStrategy;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardTest {
    private final Deck deck = new Deck();

    @BeforeEach
    void setUp() {
        List<Card> cards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<>(), new CommonStrategy()),
                new TorrentialRain("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"
                ));
        deck.insertCards(cards);
    }
    @Test
    public void cardsHaveColor() {
        Color blue = new Blue();
        new Player("Gabriel", 2, deck, new SpecialZone(List.of(),List.of(),List.of()), new CloseCombat(), new Ranged(), new Siege(), blue);

        List<Card> cards = deck.getCards();

        Assertions.assertTrue(cards.stream().allMatch(card -> card.sameColor(blue)));
    }
}
