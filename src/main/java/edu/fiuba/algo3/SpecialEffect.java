package edu.fiuba.algo3;

public interface SpecialEffect {
    // Owner effects
    void applyOnOwnerPlayer(Player player, RowType selectedRowType);
    void applyOnOwnerSide(Side side, RowType selectedRowType);
    void applyOnOwnerRow(Row row, RowType selectedRowType);
    void applyOnOwnerHand(Hand hand, RowType selectedRowType);
    void applyOnOwnerDeck(Deck deck, RowType selectedRowType);
    void applyOnOwnerDiscardPile(DiscardPile discardPile, RowType selectedRowType);
    void applyOnOwnerCard(Card card);

    // Opponent effects
    void applyOnOpponentPlayer(Player player, RowType selectedRowType);
    void applyOnOpponentSide(Side side, RowType selectedRowType);
    void applyOnOpponentRow(Row row, RowType selectedRowType);
    void applyOnOpponentHand(Hand hand, RowType selectedRowType);
    void applyOnOpponentDeck(Deck deck, RowType selectedRowType);
    void applyOnOpponentDiscardPile(DiscardPile discardPile, RowType selectedRowType);
    void applyOnOpponentCard(Card card);
}
