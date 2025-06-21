package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.cards.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.InvalidCardAmountError;
import edu.fiuba.algo3.modelo.errors.NotEnoughCardsInDeckError;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HandTest {

    private List<Card> cards;
    @BeforeEach
    void setUp(){
        cards = new ArrayList<>(Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<Modifier>()),
                new BitingFrost("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion")
        ));
    }

    @Test
    public void testSePideUnaCartaDelDeckALaHandCorrectamente(){
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();
        int expectedCards = 1;

        hand.getNCardsFromDeck(deck,1);
        int resultObtained = hand.getCardCount();

        assertEquals(expectedCards, resultObtained);
    }
    @Test
    public void testSeReparten10CardsAlaHand(){
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();

        int expectedCards = 10;

        hand.getNCardsFromDeck(deck,10);
        int resultObtained = hand.getCardCount();

        assertEquals(expectedCards, resultObtained);
    }

    @Test
    public void testNoSePuedeObtenerCartasSiElDeckEstaVacio(){
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();

        for(Card card: new ArrayList<>(cards)){
            deck.retrieveCard(card);
        }

        assertThrows(NotEnoughCardsInDeckError.class, () -> {
            hand.getNCardsFromDeck(deck,10);
        });
    }

    @Test
    public void testNoSePuedePedirMasCartasDeLasQueTieneElDeck(){
        Deck deck = new Deck();
        deck.insertCards(cards.subList(0, 8));
        Hand hand = new Hand();

        assertThrows(NotEnoughCardsInDeckError.class, () -> {
            hand.getNCardsFromDeck(deck,10);
        });
    }

    @Test
    public void testNoSePuedePedirCardsDelDeckConNumerosMenoresACero(){
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();

        assertThrows(InvalidCardAmountError.class, () ->{
            hand.getNCardsFromDeck(deck, -5);
        });
    }

    @Test
    public void testNoSePuedePedirCardsDelDeckConNumeroIgualACero(){
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();

        assertThrows(InvalidCardAmountError.class, () -> {
            hand.getNCardsFromDeck(deck,0);
        });
    }
}
