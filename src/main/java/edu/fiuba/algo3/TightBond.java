package edu.fiuba.algo3;

public class TightBond extends Modifier {
    private final String cardName;

    public TightBond(String cardName) {
        super("TightBond", "When placed next to a card with the same name, doubles the strength of both (or more) cards.");
        this.cardName = cardName;
    }

    @Override
    public void applyOnOwnerRow(Row row, RowType selectedRowType) {
        row.applyEffectOnOwnerRow(this, selectedRowType);
    }

    @Override
    public void applyOnOwnerCard(Card card) {
        if (card instanceof Unit && card.hasName(cardName)) {
            Unit unit = (Unit) card;
            unit.removeModifier(new TightBondModifier(2));
            unit.addModifier(new TightBondModifier(2));
        }
    }

    private static class TightBondModifier implements PointModifier {
        private final int multiplier;

        public TightBondModifier(int multiplier) {
            this.multiplier = multiplier;
        }

        @Override
        public int modifyPoints(int currentPoints) {
            return currentPoints * multiplier;
        }
    }
}
