package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
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

public class ImpenetrableFogTest {
    Weather fog;
    CloseCombat closeCombat1;
    CloseCombat closeCombat2;
    Ranged ranged1;
    Ranged ranged2;
    Siege siege1;
    Siege siege2;

    Player player1;
    Player player2;

    @BeforeEach
    void setUp() {
        fog = new ImpenetrableFog("niebla", "impenetrable");
        closeCombat1 = new CloseCombat();
        closeCombat2 = new CloseCombat();
        ranged1 = new Ranged();
        ranged2 = new Ranged();
        siege1 = new Siege();
        siege2 = new Siege();

        player1 = new Player("nombre", new Deck(), closeCombat1, ranged1, siege1, new Blue());
        player2 = new Player("nombre", new Deck(), closeCombat2, ranged2, siege2, new Red());
    }

    @Test
    public void testLaCartaImpenetrableFogSeAplicaCorrectamenteEnLasFilasRanged() {
        Unit carta = new Unit("carta", "comun", 6, new RangedType(), List.of());

        ranged1.placeCard(carta, new Round(player1, player2));
        ranged2.placeCard(carta, new Round(player1, player2));

        fog.apply(carta, ranged1);
        fog.apply(carta, ranged2);

        assertEquals(1, carta.calculatePoints());
    }

    @Test
    public void testLaCartaImpenetrableFogUnicamenteAfectaALasFilasRanged() {
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2);
        Unit carta1 = new Unit("carta", "comun", 6, new SiegeType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of());
        Unit carta3 = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());

        closeCombat1.placeCard(carta2, new Round(player1, player2));
        closeCombat2.placeCard(carta2, new Round(player1, player2));
        ranged1.placeCard(carta3, new Round(player1, player2));
        ranged2.placeCard(carta3, new Round(player1, player2));
        siege1.placeCard(carta1, new Round(player1, player2));
        siege2.placeCard(carta1, new Round(player1, player2));
        fog.play(specialZone);

        assertEquals(6, carta1.calculatePoints());
        assertEquals(7, carta2.calculatePoints());
        assertEquals(1, carta3.calculatePoints());
    }
}
