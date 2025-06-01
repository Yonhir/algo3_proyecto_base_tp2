package edu.fiuba.algo3.entrega_1;
import static org.mockito.Mockito.mock;
import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SideTest {

    @Test
    public void testSideColocaCartaEnCloseCombatRowCorrectamente() {

        Player jugador = new Player();
        Side side = new Side(jugador);
        Unit carta = new Unit(7, new CloseCombatRowType());


        side.placeCard(carta, new CloseCombatRowType());
        int puntos = side.calculateTotalPointsForRow(new CloseCombatRowType());


        assertEquals(7, puntos);
    }

    @Test
    public void testSideColocaCartaEnRangedRowCorrectamente() {
        Player jugador = new Player();
        Side side = new Side(jugador);
        Unit carta = new Unit(4, new RangedRowType());

        side.placeCard(carta, new RangedRowType());

        int puntos = side.calculateTotalPointsForRow(new RangedRowType());
        assertEquals(4, puntos);
    }

    @Test
    public void testSideColocaCartaEnSiegeRowCorrectamente() {
        Player jugador = new Player();
        Side side = new Side(jugador);
        Unit carta = new Unit(6, new SiegeRowType());

        side.placeCard(carta, new SiegeRowType());

        int puntos = side.calculateTotalPointsForRow(new SiegeRowType());
        assertEquals(6, puntos);
    }

    @Test
    public void testGetRowLanzaExcepcionConRowTypeDesconocido_UsandoMockito() {
        Player jugador = new Player();
        Side side = new Side(jugador);
        RowType tipoDesconocido = mock(RowType.class);

        assertThrows(IllegalArgumentException.class, () -> {
            side.placeCard(new Unit(3, tipoDesconocido), tipoDesconocido);
        });
    }
}
