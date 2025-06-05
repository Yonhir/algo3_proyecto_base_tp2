package edu.fiuba.algo3.architecture;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void player_plays_card_ranged() {
        Card cardArcher = new Unit("Arquero", "NA", 10, new Ranged());
        Player player = new Player("Gabriel", 2);
        player.playCard(cardArcher, new Ranged());

        player.cleanSide();

        CardCollection cards_used = player.getDiscardPile();

        assertTrue(cards_used.contains(cardArcher));
    }

    @Test
    public void player_plays_card_closeCombat() {
        Card cardPaladin = new Unit("Paladino", "NA", 10, new CloseCombat());
        Player player = new Player("Gabriel", 2);
        player.playCard(cardPaladin, new CloseCombat());

        player.cleanSide();

        CardCollection cards_used = player.getDiscardPile();

        assertTrue(cards_used.contains(cardPaladin));
    }

    @Test
    public void player_plays_card_siege() {
        Card cardSiege = new Unit("Catapulta", "NA", 10, new Siege());
        Player player = new Player("Gabriel", 2);
        player.playCard(cardSiege, new Siege());

        player.cleanSide();

        CardCollection cards_used = player.getDiscardPile();

        assertTrue(cards_used.contains(cardSiege));
    }

    @Test
    public void player_plays_card_error() {
        Card cardSiege = new Unit("Catapulta", "NA", 10, new Siege());
        Player player = new Player("Gabriel", 2);
        player.playCard(cardSiege, new Ranged());

        player.cleanSide();

        CardCollection cards_used = player.getDiscardPile();

        assertFalse(cards_used.contains(cardSiege));
    }

    @Test
    public void points_plays_card() {
        //---------------------------------------------------------------------------------------------
        Card cardArcher = new Unit("Arquero", "NA", 10, new Ranged());
        int expected = 10;
        //---------------------------------------------------------------------------------------------
        Player player = new Player("Gabriel", 2);
        player.playCard(cardArcher, new Ranged());

        int res = player.calculatePoints();

        assertEquals(expected, res);
    }

}
