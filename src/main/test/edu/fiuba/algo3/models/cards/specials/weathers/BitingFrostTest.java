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

public class BitingFrostTest {
    Weather frost;
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
        player1 = new Player("nombre", new Blue());
        player2 = new Player("nombre", new Red());

        discardPile1 = player1.getDiscardPile();
        discardPile2 = player2.getDiscardPile();
        frost = new BitingFrost("escarcha", "wow");
        frost.setColor(new Blue());
        closeCombat1 = player1.getCloseCombatRow();
        closeCombat2 = player2.getCloseCombatRow();
        ranged1 = player1.getRangedRow();
        ranged2 = player2.getRangedRow();
        siege1 = player1.getSiegeRow();
        siege2 = player2.getSiegeRow();
    }

    @Test
    public void testLaCartaBitingFrostSeAplicaCorrectamenteEnLasFilasCloseCombat() {
        Unit carta_owner = new Unit("carta", "comun", 8, new CloseCombatType(), List.of());
        Unit carta_opponent = new Unit("carta", "comun", 8, new CloseCombatType(), List.of());
        carta_owner.setColor(new Blue());
        carta_opponent.setColor(new Red());

        closeCombat1.placeCard(carta_owner, new Round(player1, player2));
        closeCombat2.placeCard(carta_opponent, new Round(player1, player2));

        frost.apply(carta_owner, closeCombat1);
        frost.apply(carta_opponent, closeCombat2);

        assertEquals(1, carta_owner.calculatePoints());
        assertEquals(1, carta_opponent.calculatePoints());
    }

    @Test
    public void testLaCartaBitingFrostUnicamenteAfectaALasFilasCloseCombat() {
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2, discardPile1, discardPile2);
        Unit cartaOwner1 = new Unit("carta", "comun", 2, new SiegeType(), List.of());
        Unit cartaOwner2 = new Unit("nombre", "descripcion", 4, new CloseCombatType(), List.of());
        Unit cartaOwner3 = new Unit("nombre", "descripcion", 8, new RangedType(), List.of());
        Unit cartaOpponent1 = new Unit("carta", "comun", 2, new SiegeType(), List.of());
        Unit cartaOpponent2 = new Unit("nombre", "descripcion", 4, new CloseCombatType(), List.of());
        Unit cartaOpponent3 = new Unit("nombre", "descripcion", 8, new RangedType(), List.of());
        cartaOwner1.setColor(new Blue());
        cartaOwner2.setColor(new Blue());
        cartaOwner3.setColor(new Blue());
        cartaOpponent1.setColor(new Red());
        cartaOpponent2.setColor(new Red());
        cartaOpponent3.setColor(new Red());

        closeCombat1.placeCard(cartaOwner2, new Round(player1, player2));
        closeCombat2.placeCard(cartaOpponent2, new Round(player1, player2));
        ranged1.placeCard(cartaOwner3, new Round(player1, player2));
        ranged2.placeCard(cartaOpponent3, new Round(player1, player2));
        siege1.placeCard(cartaOwner1, new Round(player1, player2));
        siege2.placeCard(cartaOpponent1, new Round(player1, player2));
        frost.play(specialZone);

        assertEquals(2, cartaOwner1.calculatePoints());
        assertEquals(8, cartaOwner3.calculatePoints());
        assertEquals(1, cartaOwner2.calculatePoints());
    }
}
