package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.MoraleBoost;

public class SpecialTest {
    @Test
    public void testSpecialCreation() {
        // Arrange:
        String nombre = "Soldado";
        String descripcion = "Unidad basica de combate cuerpo a cuerpo";
        MoraleBoost card = new MoraleBoost(nombre, descripcion);
    }
}
