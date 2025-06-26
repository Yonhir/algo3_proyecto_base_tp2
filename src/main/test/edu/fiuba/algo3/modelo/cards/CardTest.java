package edu.fiuba.algo3.modelo.cards;

import edu.fiuba.algo3.modelo.colors.*;
import edu.fiuba.algo3.modelo.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SectionType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private final Deck deck = new Deck();

    @BeforeEach
    void setUp() {
        List<Card> cards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<>()),
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
    public void testSeLePuedeColocarALaCartaUnPlayerColorCorrectamente() {
        PlayerColor red = new Red();
        Card card = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());

        card.setColor(red);

        assertDoesNotThrow(() -> card.verifyColor(red));
    }

    @Test
    public void testSeVerificaCorrectamenteQueLaCartaTieneLaFilaBuscada() {
        SectionType closeCombat = new CloseCombatType();
        Card card = new Unit("Nombre", "Descripcion", 4, closeCombat, new ArrayList<>());

        assertDoesNotThrow(() -> card.verifySectionType(closeCombat));
    }

    @Test
    public void testSeVerificaCorrectamenteQueLaCartaNoTieneLaSeccionBuscada() {
        SectionType closeCombat = new CloseCombatType();
        SectionType ranged = new RangedType();
        Card card = new Unit("Nombre", "Descripcion", 4, closeCombat, new ArrayList<>());

        assertThrows(SectionTypeMismatchError.class, () -> card.verifySectionType(ranged));
    }

    @Test
    public void testLaCartaSeCreaConElColorGreen() {
        Card carta = new Unit("unidad", "comun", 5, new CloseCombatType(), List.of());

        assertTrue(carta.haveSameColor(new Green()));
    }
}
