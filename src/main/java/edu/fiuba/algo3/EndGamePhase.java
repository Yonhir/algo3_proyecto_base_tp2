package edu.fiuba.algo3;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EndGamePhase implements Phase {
    private final Player player1;
    private final Player player2;
    private final List<Round> rounds;

    public EndGamePhase(Player player1, Player player2, List<Round> rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = rounds;
    }

    @Override
    public DeckBuilder getDeckBuilder() {
        throw new IllegalStateException("Cannot get deck builder during end game phase");
    }

    @Override
    public Phase registerPlayer(String name, Deck deck) {
        throw new IllegalStateException("Cannot register player during end game phase");
    }

    @Override
    public List<Card> getCurrentPlayerHand() {
        throw new IllegalStateException("No player hand available during end game phase");
    }

    @Override
    public Phase drawAndDiscard(Card cardToDiscard) {
        throw new IllegalStateException("Cannot draw and discard during end game phase");
    }

    @Override
    public Phase confirmHand() {
        throw new IllegalStateException("Cannot confirm hand during end game phase");
    }

    @Override
    public Phase playCardFromHand(Card card, RowType selectedRowType) {
        throw new IllegalStateException("Cannot play cards during end game phase");
    }

    @Override
    public Phase playerPassRound() {
        throw new IllegalStateException("Cannot pass round during end game phase");
    }

    @Override
    public Map<String, Object> getRoundsResults() {
        Map<Integer, Map<String, Object>> roundsResults = new HashMap<>();
        for (Round round : rounds) {
            Map<String, Object> roundResult = new HashMap<>();
            roundResult.put("player1Points", round.getPlayerPoints(player1));
            roundResult.put("player2Points", round.getPlayerPoints(player2));
            roundResult.put("player1Name", round.currentPlayer().getName());
            roundResult.put("player2Name", round.opponentPlayer().getName());
            roundResult.put("winner", round.getRoundWinner());
            roundsResults.put(round.getRoundNumber(), roundResult);
        }
        Map<String, Object> finalResults = new HashMap<>();
        finalResults.put("rounds", roundsResults);
        finalResults.put("gameWinner", getGameWinner());
        return finalResults;
    }

    private String getGameWinner() {
        if (player1.getHealth() <= 0 && player2.getHealth() <= 0) {
            return "Tie";
        } else if (player1.getHealth() <= 0) {
            return player2.getName();
        } else if (player2.getHealth() <= 0) {
            return player1.getName();
        }
        return null;
    }
}
