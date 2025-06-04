package edu.fiuba.algo3.entrega_1;

import static org.mockito.Mockito.mock;
import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SideTest {

    @Test
    public void testSideColocaCartaEnCloseCombatRowCorrectamente() {
        Side side = new Side();
        int puntosCarta = 7;
        Unit carta = new Unit("NombreGenerico", "Unidad cuerpo a cuerpo", puntosCarta, new CloseCombat());

        side.placeCard(carta, new CloseCombat());
        int puntos = side.calculateTotalPointsForRow(new CloseCombat());

        assertEquals(puntosCarta, puntos);
    }

    @Test
    public void testSideColocaCartaEnRangedRowCorrectamente() {
        Side side = new Side();
        int puntosCarta = 4;
        Unit carta = new Unit("NombreGenerico", "Unidad de largo alcance", puntosCarta, new Ranged());

        side.placeCard(carta, new Ranged());

        int puntos = side.calculateTotalPointsForRow(new Ranged());
        assertEquals(puntosCarta, puntos);
    }

    @Test
    public void testSideColocaCartaEnSiegeRowCorrectamente() {
        Side side = new Side();
        int puntosCarta = 6;
        Unit carta = new Unit("NombreGenerico", "Unidad de asedio", puntosCarta, new Siege());

        side.placeCard(carta, new Siege());

        int puntos = side.calculateTotalPointsForRow(new Siege());
        assertEquals(puntosCarta, puntos);
    }

    @Test
    public void testGetRowLanzaExcepcionConRowTypeDesconocido_UsandoMockito() {
        Side side = new Side();
        RowType tipoDesconocido = mock(RowType.class);

        assertThrows(IllegalArgumentException.class, () -> {
            Unit carta = new Unit("Desconocido", "No pertenece a ninguna fila", 3, tipoDesconocido);
            side.placeCard(carta, tipoDesconocido);
        });
    }
}
