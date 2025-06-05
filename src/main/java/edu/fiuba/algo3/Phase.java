package edu.fiuba.algo3;

import java.util.List;
import java.util.Map;

public interface Phase {
    DeckBuilder getDeckBuilder();
    Phase registerPlayer(String name, Deck deck);
    List<Card> getCurrentPlayerHand();
    Phase drawAndDiscard(Card cardToDiscard);
    Phase confirmHand();
    Phase playCardFromHand(Card card, RowType selectedRowType);
    Phase playerPassRound();
    Map<String, Object> getRoundsResults();
}
