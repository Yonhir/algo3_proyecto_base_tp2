package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Row;
import edu.fiuba.algo3.Unit;
import edu.fiuba.algo3.CloseCombat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RowTest {

    @Test
    public void testRowGuardaUnaUnidadCorrectamente() {
        Row row = new Row(new CloseCombat());
        int puntosBase = 5;
        Unit card = new Unit("NombreGenerico", "Unidad básica cuerpo a cuerpo", puntosBase, new CloseCombat());

        row.placeCard(card);

        assertTrue(row.getCards().contains(card));
    }

    @Test
    public void testRowCalculaCorrectamenteElPuntajeTotalConUnaUnidad() {
        Row row = new Row(new CloseCombat());
        int puntosBase = 5;
        Unit card = new Unit("NombreGenerico", "Unidad básica cuerpo a cuerpo", puntosBase, new CloseCombat());

        row.placeCard(card);

        assertEquals(puntosBase, row.calculateTotalPoints());
    }
}
