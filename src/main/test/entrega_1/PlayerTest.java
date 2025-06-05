package entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTest {
    @Test
    public void testPlayerSeLeReparten10CartasDeSuMazoCorrectamente(){
        List<Card> cardsUnits = Arrays.asList(
                new Unit("Cow", "", 0, new Ranged()),
                new Unit("Dethmold", "", 6, new Ranged()),
                new Unit("Harpy", "", 2, new Ranged()),
                new Unit("Toad", "", 7, new Ranged()),
                new Unit("Cynthia", "", 4, new Ranged()),
                new Unit("Ermion", "", 8, new Ranged()),
                new Unit("Ballista", "", 6, new Siege()),
                new Unit("Catapult", "", 8, new Siege()),
                new Unit("Thaler", "", 1, new Siege()),
                new Unit("Ice Giant", "", 5, new Siege()),
                new Unit("Ice Giant", "", 10, new Siege()),
                new Unit("Thaler", "", 4, new Siege()),
                new Unit("Dethmold", "", 4, new Ranged()),
                new Unit("Harpy", "", 4, new Ranged()),
                new Unit("Toad", "", 2, new Ranged()));
        List<Card> cardsSpecial = Arrays.asList(
                new Decoy("Decoy", ""),
                new Mardroeme("Mardroeme", ""),
                new Scorch("Scorch", ""),
                new CommandersHorn("Commander's Horn", ""),
                new Mardroeme("Mardroeme", ""),
                new Scorch("Scorch", ""));

        Deck deck = new Deck(cardsUnits, cardsSpecial);
        Hand hand = new Hand(new ArrayList<>(), new ArrayList<>());
        DiscardPile discardPile = new DiscardPile(new ArrayList<>(), new ArrayList<>());
        Player player = new Player("Andy", 2, deck, hand, discardPile);
        int cardsInHandExpected = 10;

        List<Card> cardsNRandom = player.getNCardsFromDeck(10);
        player.putCardsInHand(cardsNRandom);
        CardCollection handPlayer = player.getHand();

        assertEquals(handPlayer.getCardCount(),cardsInHandExpected);
    }
}
