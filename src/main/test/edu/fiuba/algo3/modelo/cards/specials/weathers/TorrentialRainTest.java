package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TorrentialRainTest {
    Weather rain;
    CloseCombat closeCombat1;
    CloseCombat closeCombat2;
    Ranged ranged1;
    Ranged ranged2;
    Siege siege1;
    Siege siege2;

    Player player1;
    Player player2;
    DiscardPile discardPile1;
    DiscardPile discardPile2;

    @BeforeEach
    void setUp() {
        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        rain = new TorrentialRain("lluvia", "torrencial");
        closeCombat1 = new CloseCombat(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged1 = new Ranged(discardPile1);
        ranged2 = new Ranged(discardPile2);
        siege1 = new Siege(discardPile1);
        siege2 = new Siege(discardPile2);

        player1 = new Player("nombre", new Deck(), closeCombat1, ranged1, siege1, new Blue());
        player2 = new Player("nombre", new Deck(), closeCombat2, ranged2, siege2, new Red());
    }

    @Test
    public void testLaCartaTorrentialRainSeAplicaCorrectamenteEnLasFilasSiege() {
        Unit carta = new Unit("carta", "comun", 6, new SiegeType(), List.of());

        siege1.placeCard(carta, new Round(player1, player2));
        siege2.placeCard(carta, new Round(player1, player2));

        rain.apply(carta, siege1);
        rain.apply(carta, siege2);

        assertEquals(1, carta.calculatePoints());
    }

    @Test
    public void testLaCartaTorrentialRainUnicamenteAfectaALasFilasSiege() {
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2, discardPile1, discardPile2);
        Unit carta1 = new Unit("carta", "comun", 6, new SiegeType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of());
        Unit carta3 = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());

        closeCombat1.placeCard(carta2, new Round(player1, player2));
        closeCombat2.placeCard(carta2, new Round(player1, player2));
        ranged1.placeCard(carta3, new Round(player1, player2));
        ranged2.placeCard(carta3, new Round(player1, player2));
        siege1.placeCard(carta1, new Round(player1, player2));
        siege2.placeCard(carta1, new Round(player1, player2));
        rain.play(specialZone);

        assertEquals(7, carta2.calculatePoints());
        assertEquals(5, carta3.calculatePoints());
        assertEquals(1, carta1.calculatePoints());
    }
}
