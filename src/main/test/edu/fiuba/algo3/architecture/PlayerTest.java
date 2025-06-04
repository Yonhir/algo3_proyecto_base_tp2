package edu.fiuba.algo3.architecture;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void player_plays_card() {
        Card cardArcher = new Unit("Arquero", "NA", 10, new Ranged());
        Player player = new Player("Gabriel", 2);
        player.playCard(cardArcher, new Ranged());

        player.cleanSide();

        ArrayList<Card> cards_used = player.getDiscardPile();

        assertTrue(cards_used.contains(cardArcher));
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
