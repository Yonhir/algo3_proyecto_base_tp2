package edu.fiuba.algo3;

abstract public class Special extends Card implements SpecialEffect {
    public Special(String name, String description) {
        super(name, description);
    }

    @Override
    public void play(Player owner, Player opponent, RowType selectedRowType) {
        owner.playCard(this, selectedRowType);
        owner.applyEffectOnOwner(this, selectedRowType);
        opponent.applyEffectOnOpponent(this, selectedRowType);
    }

    @Override
    public void applyOnOwnerPlayer(Player player, RowType selectedRowType) {}

    @Override
    public void applyOnOwnerSide(Side side, RowType selectedRowType) {}

    @Override
    public void applyOnOwnerRow(Row row, RowType selectedRowType) {}

    @Override
    public void applyOnOwnerHand(Hand hand, RowType selectedRowType) {}

    @Override
    public void applyOnOwnerDeck(Deck deck, RowType selectedRowType) {}

    @Override
    public void applyOnOwnerDiscardPile(DiscardPile discardPile, RowType selectedRowType) {}

    @Override
    public void applyOnOwnerCard(Card card) {}

    @Override
    public void applyOnOpponentPlayer(Player player, RowType selectedRowType) {}

    @Override
    public void applyOnOpponentSide(Side side, RowType selectedRowType) {}

    @Override
    public void applyOnOpponentRow(Row row, RowType selectedRowType) {}

    @Override
    public void applyOnOpponentHand(Hand hand, RowType selectedRowType) {}

    @Override
    public void applyOnOpponentDeck(Deck deck, RowType selectedRowType) {}

    @Override
    public void applyOnOpponentDiscardPile(DiscardPile discardPile, RowType selectedRowType) {}

    @Override
    public void applyOnOpponentCard(Card card) {}
}
