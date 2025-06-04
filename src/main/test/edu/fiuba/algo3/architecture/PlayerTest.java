package edu.fiuba.algo3.architecture;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void cards_in_discardPile() {
        Card cardPaladin = new Unit("Paladino", "NA", 10, new CloseCombat());
        Card cardSiege = new Unit("Catapulta", "NA", 10, new Siege());
        Card cardArcher = new Unit("Arquero", "NA", 10, new Ranged());

        CardCollection expected = new CardCollection();
        expected.addCard(cardSiege);
        expected.addCard(cardArcher);
        expected.addCard(cardPaladin);

        Player player = new Player("Gabriel", 2);

        player.playCard(cardSiege, new Siege());
        player.playCard(cardArcher, new Ranged());
        player.playCard(cardPaladin, new CloseCombat());

        player.cleanSide();

        CardCollection cards_used = player.getDiscardPile();

        Assertions.assertTrue(expected.equalsContent(cards_used));
    }
}
