package edu.fiuba.algo3.entrega_1;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.CloseCombat;
import edu.fiuba.algo3.Row;
import edu.fiuba.algo3.RowType;
import edu.fiuba.algo3.Unit;

public class RowTest {
    @Test
    public void testAddUnitsAndCalculateTotalPoints() {
        RowType tipoFila = new CloseCombat();
        Row row = new Row(tipoFila);
        Unit unidad1 = new Unit("Soldado", "Unidad basica", 5, tipoFila, new ArrayList<>());
        Unit unidad2 = new Unit("Arquero", "Unidad a distancia", 3, tipoFila, new ArrayList<>());

        row.placeCard(unidad1);
        row.placeCard(unidad2);

        assertEquals(8, row.calculateTotalPoints());
    }
}
