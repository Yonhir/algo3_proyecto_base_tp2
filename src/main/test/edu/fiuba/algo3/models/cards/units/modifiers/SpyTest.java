package edu.fiuba.algo3.models.cards.units.modifiers;


import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.models.cards.specials.MoraleBoost;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpyTest {
    private Ranged RangedRowOpponent;
    private Ranged RangedRowOwner;

    private Unit carta_espia;
    private Hand hand;
    private Deck deck;

    private Round round;

    private List<Card> cards;
    private DiscardPile discardPile;
    @BeforeEach
    void setUp(){
        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();
        SectionType cct = new CloseCombatType();
        SectionType r = new RangedType();
        SectionType s = new SiegeType();

        cards = new ArrayList<>(Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, r, new ArrayList<>()),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r))));

        RangedRowOwner = new Ranged(discardPile1);
        CloseCombat closeCombat = new CloseCombat(discardPile1);
        Siege siege = new Siege(discardPile1);

        RangedRowOpponent = new Ranged(discardPile2);
        CloseCombat closeCombat2 = new CloseCombat(discardPile2);
        Siege siege2 = new Siege(discardPile2);

        deck = new Deck();
        deck.insertCards(cards);

        hand = new Hand();

        carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand)));

        carta_espia.setColor(new Blue());

        Player player = new Player("Gabriel", deck, discardPile1, closeCombat, RangedRowOwner, siege, new Blue());
        Player opponent = new Player("Juan", new Deck(), discardPile2, closeCombat2, RangedRowOpponent, siege2, new Red());
        round = new Round(player, opponent);
    }
    @Test
    public void testLaCartaSeJuegaEnLasFilasPropiasException() {
        assertThrows(SectionPlayerMismatchError.class, () -> RangedRowOwner.placeCard(carta_espia, round));
    }

    @Test
    public void testSeJuegaLaCartaEspiaSeTomanCartasDelMazoYVanALaManoPropia() {
        int expectedCardsInHand = 2;

        RangedRowOpponent.placeCard(carta_espia, round);

        assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void testElMazoPierdeLasCantidadDeCartasCorrectaAlJugarseLaCartaConModificadorEspia() {
        int expectedCardsInHand = deck.getCardCount() - 2;

        RangedRowOpponent.placeCard(carta_espia, round);

        assertEquals(expectedCardsInHand, deck.getCardCount());
    }

}
