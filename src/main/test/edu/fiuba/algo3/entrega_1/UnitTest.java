package edu.fiuba.algo3.entrega_1;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.CloseCombat;
import edu.fiuba.algo3.RowType;
import edu.fiuba.algo3.Unit;

public class UnitTest {
    @Test
    public void testUnitCreationAndGetPoints() {
        // Arrange:
        String nombre = "Soldado";
        String description = "Unidad basica de combate cuerpo a cuerpo";
        int puntosBase = 5;
        RowType tipoFila = new CloseCombat();
        Unit unit = new Unit(nombre, description, puntosBase, tipoFila, new ArrayList<>());

        // Assert:
        assertEquals(puntosBase, unit.getPoints());
    }
}
