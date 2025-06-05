package edu.fiuba.algo3;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Round implements Phase {
    private final int roundNumber;
    private int winnerPoints;
    private int loserPoints;
    private Player winner;
    private Player loser;
    private Player currentPlayer;
    private Player opponentPlayer;
    private boolean playerHasPassed;
    private List<Round> rounds;

    public Round(Player titular, Player nextPlayer, int roundNumber, List<Round> rounds) {
        this.currentPlayer = titular;
        this.opponentPlayer = nextPlayer;
        this.roundNumber = roundNumber;
        this.playerHasPassed = false;
        this.rounds = rounds;
    }

    @Override
    public DeckBuilder getDeckBuilder() {
        throw new IllegalStateException("Cannot get deck builder during game round");
    }

    @Override
    public Phase registerPlayer(String name, Deck deck) {
        throw new IllegalStateException("Cannot register player during game round");
    }

    @Override
    public List<Card> getCurrentPlayerHand() {
        return currentPlayer.getHand();
    }

    @Override
    public Phase drawAndDiscard(Card cardToDiscard) {
        throw new IllegalStateException("Cannot draw and discard during game round");
    }

    @Override
    public Phase confirmHand() {
        throw new IllegalStateException("Cannot confirm hand during game round");
    }

    @Override
    public Phase playCardFromHand(Card card, RowType selectedRowType) {
        currentPlayer.playCard(card, selectedRowType);
        return nextTurn();
    }

    @Override
    public Phase playerPassRound() {
        if (playerHasPassed) {
            return endRound();
        }
        playerHasPassed = true;
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        return nextTurn();
    }

    private Phase nextTurn() {
        if (playerHasPassed) {
            return this;
        }
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        return this;
    }

    private Phase endRound() {
        getWinnerAndLoserPoints();
        currentPlayer.clearBoard();
        opponentPlayer.clearBoard();
        if (winner == null) {
            currentPlayer.loseHealth();
            opponentPlayer.loseHealth();
        } else {
            loser.loseHealth();
        }
        rounds.add(this);
        if (isEndGame()) {
            return new EndGamePhase(currentPlayer, opponentPlayer, rounds);
        } else {
            return new Round(opponentPlayer, currentPlayer, roundNumber + 1, rounds);
        }
    }

    @Override
    public Map<String, Object> getRoundsResults() {
        Map<Integer, Map<String, Object>> roundsResults = new HashMap<>();
        for (Round round : rounds) {
            Map<String, Object> roundResult = new HashMap<>();
            roundResult.put("player1Points", round.getPlayerPoints(currentPlayer));
            roundResult.put("player2Points", round.getPlayerPoints(opponentPlayer));
            roundResult.put("player1Name", round.currentPlayer().getName());
            roundResult.put("player2Name", round.opponentPlayer().getName());
            roundResult.put("winner", round.getRoundWinner());
            roundsResults.put(round.getRoundNumber(), roundResult);
        }
        Map<String, Object> finalResults = new HashMap<>();
        finalResults.put("rounds", roundsResults);
        finalResults.put("gameWinner", getRoundWinner());
        return finalResults;
    }

    private void getWinnerAndLoserPoints() {
        int player1Points = currentPlayer.calculatePoints();
        int player2Points = opponentPlayer.calculatePoints();
        if (player1Points > player2Points) {
            winnerPoints = player1Points;
            winner = currentPlayer;
            loserPoints = player2Points;
            loser = opponentPlayer;
        } else if (player2Points > player1Points) {
            winnerPoints = player2Points;
            winner = opponentPlayer;
            loserPoints = player1Points;
            loser = currentPlayer;
        } else {
            loserPoints = player2Points;
        }
    }

    public Player winner() {
        return winner;
    }

    public Player loser() {
        return loser;
    }

    public int getPlayerPoints(Player player) {
        if (winner == player) {
            return winnerPoints;
        } else if (loser == player || winner == null) {
            return loserPoints;
        } else {
            return 0;
        }
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public Player opponentPlayer() {
        return opponentPlayer;
    }

    public String getRoundWinner() {
        if (winner != null) {
            return winner.getName();
        } else {
            return "Tie";
        }
    }

    private boolean isEndGame() {
        return (currentPlayer.getHealth() <= 0 || opponentPlayer.getHealth() <= 0);
    }
}
