package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.units.Unit;
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

public class SpyTest {
    private CloseCombatType closeCombatType ;
    private RangedType rangedType ;
    private SiegeType siegeType ;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;

    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;

    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;

    private List<Card> cards;
    @BeforeEach
    void setUp(){
        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        closeCombat1 = new CloseCombat(discardPile1);
        ranged1 = new Ranged(discardPile1);
        siege1 = new Siege(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged2 = new Ranged(discardPile2);
        siege2 = new Siege(discardPile2);
        closeCombatType = new CloseCombatType();
        rangedType = new RangedType();
        siegeType = new SiegeType();
        deck = new Deck();
        player = new Player("Gabriel", deck, closeCombat1, ranged1, siege1, new Blue());
        opponent = new Player("Juan", deck, closeCombat2, ranged2, siege2, new Red());
        round = new Round(player, opponent);
        cards = new ArrayList<>(Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, closeCombatType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, closeCombatType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, rangedType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, siegeType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, closeCombatType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, siegeType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, siegeType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, rangedType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, rangedType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, closeCombatType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, rangedType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, siegeType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, siegeType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, closeCombatType, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, rangedType, new ArrayList<>()),
                new MoraleBoost("Nombre", "Descripcion", List.of(rangedType)),
                new MoraleBoost("Nombre", "Descripcion", List.of(rangedType)),
                new MoraleBoost("Nombre", "Descripcion", List.of(rangedType)),
                new MoraleBoost("Nombre", "Descripcion", List.of(rangedType)),
                new MoraleBoost("Nombre", "Descripcion", List.of(rangedType)),
                new MoraleBoost("Nombre", "Descripcion", List.of(rangedType))));
    }

    @Test
    public void testSeJuegaLaCartaEspiaSeTomanCartasDelMazoYVanALaManoPropia() {
        deck.insertCards(cards);
        Hand hand = new Hand();
        Unit cartaEspia = new Unit("Nombre", "Descripcion", 4, rangedType, List.of(new Spy(deck, hand, ranged1)));
        int expectedCardsInHand = 2;

        ranged2.placeCard(cartaEspia, round);

        Assertions.assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void testElMazoPierdeLasCantidadDeCartasCorrectaAlJugarseLaCartaConModificadorEspia() {
        deck.insertCards(cards);
        Hand hand = new Hand();
        Unit cartaEspia = new Unit("Nombre", "Descripcion", 4, rangedType, List.of(new Spy(deck, hand, ranged1)));
        int expectedCardsInHand = deck.getCardCount() - 2;

        ranged2.placeCard(cartaEspia, round);

        Assertions.assertEquals(expectedCardsInHand, deck.getCardCount());
    }
}
