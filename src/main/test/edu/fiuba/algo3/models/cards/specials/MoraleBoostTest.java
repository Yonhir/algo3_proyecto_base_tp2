package edu.fiuba.algo3.models.cards.specials;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.models.sections.rows.*;
import edu.fiuba.algo3.models.sections.types.*;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoraleBoostTest {
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    private MoraleBoost moraleBoost;


    @BeforeEach
    void setUp() {
        DiscardPile discardPile = new DiscardPile();
        closeCombat = new CloseCombat(discardPile);
        ranged = new Ranged(discardPile);
        siege = new Siege(discardPile);

        moraleBoost = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()));

        ranged.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()));

        siege.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>()));
    }

    @Test
    public void testCartaEspecialMoraleBoostEnFilaCloseCombat() {
        int expectedPoints = closeCombat.calculatePoints() * 2;

        moraleBoost.play(closeCombat);

        int actualPoints = closeCombat.calculatePoints();

        assertEquals(expectedPoints, actualPoints);

    }

    @Test
    public void testCartaEspecialMoraleBoostEnFilaRanged() {
        int expectedPoints = ranged.calculatePoints() * 2;

        moraleBoost.play(ranged);

        int actualPoints = ranged.calculatePoints();

        assertEquals(expectedPoints, actualPoints);

    }

    @Test
    public void testCartaEspecialMoraleBoostEnFilaSiege() {
        int expectedPoints = siege.calculatePoints() * 2;

        moraleBoost.play(siege);

        int actualPoints = siege.calculatePoints();

        assertEquals(expectedPoints, actualPoints);

    }

    @Test
    public void testCartaEspecialMoraleBoostEnFilaVacia() {
        int expectedPoints = 0;

        siege.discardCards();

        moraleBoost.play(siege);

        int actualPoints = siege.calculatePoints();

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testLaCartaNoSePuedeJugarEnElSideEnemigoException(){
        moraleBoost.setColor(new Blue());
        siege.setColor(new Red());

        Player player = new Player("player", new Red());
        Player player1 = new Player("player", new Blue());

        assertThrows(SectionPlayerMismatchError.class, () -> siege.placeCard(moraleBoost, new Round(player, player1)));
    }
}