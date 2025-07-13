package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
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
        rain.setColor(new Blue());
        closeCombat1 = new CloseCombat(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged1 = new Ranged(discardPile1);
        ranged2 = new Ranged(discardPile2);
        siege1 = new Siege(discardPile1);
        siege2 = new Siege(discardPile2);

        player1 = new Player("nombre", new Blue());
        player2 = new Player("nombre", new Red());
    }

    @Test
    public void testLaCartaTorrentialRainSeAplicaCorrectamenteEnLasFilasSiege() {
        Unit carta = new Unit("carta", "comun", 6, new SiegeType(), List.of());
        Unit cartaNueva = new Unit("cartaNueva", "comun", 6, new SiegeType(), List.of());
        carta.setColor(new Blue());
        cartaNueva.setColor(new Red());

        siege1.placeCard(carta, new Round(player1, player2));
        siege2.placeCard(cartaNueva, new Round(player1, player2));

        rain.apply(carta, siege1);
        rain.apply(cartaNueva, siege2);

        assertEquals(1, carta.calculatePoints());
        assertEquals(1, cartaNueva.calculatePoints());
    }

    @Test
    public void testLaCartaTorrentialRainUnicamenteAfectaALasFilasSiege() {
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2, discardPile1, discardPile2);
        Unit carta1 = new Unit("carta", "comun", 6, new SiegeType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of());
        Unit carta3 = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());
        Unit carta4 = new Unit("cartaRed", "comun", 6, new SiegeType(), List.of());
        Unit carta5 = new Unit("nombreRed", "descripcion", 7, new CloseCombatType(), List.of());
        Unit carta6 = new Unit("nombreRed", "descripcion", 5, new RangedType(), List.of());

        carta1.setColor(new Blue());
        carta2.setColor(new Blue());
        carta3.setColor(new Blue());
        carta4.setColor(new Red());
        carta5.setColor(new Red());
        carta6.setColor(new Red());

        closeCombat1.placeCard(carta2, new Round(player1, player2));
        closeCombat2.placeCard(carta5, new Round(player1, player2));
        ranged1.placeCard(carta3, new Round(player1, player2));
        ranged2.placeCard(carta6, new Round(player1, player2));
        siege1.placeCard(carta1, new Round(player1, player2));
        siege2.placeCard(carta4, new Round(player1, player2));
        rain.play(specialZone);

        assertEquals(7, carta2.calculatePoints());
        assertEquals(5, carta3.calculatePoints());
        assertEquals(1, carta1.calculatePoints());
        assertEquals(7, carta5.calculatePoints());
        assertEquals(5, carta6.calculatePoints());
        assertEquals(1, carta4.calculatePoints());
    }
}
