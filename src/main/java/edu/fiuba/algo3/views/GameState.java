package edu.fiuba.algo3.views;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.turnManagement.Game;

public class GameState {
    public final Hand currentPlayerHand;
    public final Deck player1Deck, player2Deck;
    public final DiscardPile player1DiscardPile, player2DiscardPile;
    public final CloseCombat player1CloseCombat, player2CloseCombat;
    public final Ranged player1Ranged, player2Ranged;
    public final Siege player1Siege, player2Siege;
    public final SpecialZone specialZone;
    public final Game game;

    public GameState(Hand currentPlayerHand,
                     Deck player1Deck, Deck player2Deck,
                     DiscardPile player1DiscardPile, DiscardPile player2DiscardPile,
                     CloseCombat player1CloseCombat, CloseCombat player2CloseCombat,
                     Ranged player1Ranged, Ranged player2Ranged,
                     Siege player1Siege, Siege player2Siege,
                     SpecialZone specialZone,
                     Game game) {
        this.currentPlayerHand = currentPlayerHand;
        this.player1Deck = player1Deck;
        this.player2Deck = player2Deck;
        this.player1DiscardPile = player1DiscardPile;
        this.player2DiscardPile = player2DiscardPile;
        this.player1CloseCombat = player1CloseCombat;
        this.player2CloseCombat = player2CloseCombat;
        this.player1Ranged = player1Ranged;
        this.player2Ranged = player2Ranged;
        this.player1Siege = player1Siege;
        this.player2Siege = player2Siege;
        this.specialZone = specialZone;
        this.game = game;
    }
}

