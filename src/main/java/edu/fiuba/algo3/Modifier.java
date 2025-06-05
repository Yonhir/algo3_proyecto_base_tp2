package edu.fiuba.algo3;

abstract public class Modifier implements SpecialEffect {
    private String name;
    private String description;

    public Modifier(String name, String description) {
        this.name = name;
        this.description = description;
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
