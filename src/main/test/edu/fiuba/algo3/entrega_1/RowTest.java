package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Row;
import edu.fiuba.algo3.Unit;
import edu.fiuba.algo3.CloseCombatRowType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RowTest {
    @Test
    public void testUnRowGuardaCorrectamenteUnaUnit() {
        Row row = new Row(new CloseCombatRowType());
        Unit card = new Unit(5, new CloseCombatRowType());

        row.placeCard(card);

        assertTrue(row.getCards().contains(card));
        assertEquals(5, row.calculateTotalPoints());
    }
}