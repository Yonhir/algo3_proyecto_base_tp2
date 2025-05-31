package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SideTest {

    @Test
    public void testSideColocaCartaEnFilaCorrecta() {

        Player jugador = new Player();
        Side side = new Side(jugador);
        Unit carta = new Unit(7, new CloseCombatRowType());


        side.placeCard(carta, new CloseCombatRowType());
        int puntos = side.calculateTotalPointsForRow(new CloseCombatRowType());


        assertEquals(7, puntos);
    }
}
