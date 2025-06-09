package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RowTest {
    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesCorrectamente() {
        Row cuerpoACuerpo = new CloseCombat();

        Card tierraArrasada = new Scorch("Nombre", "Descripcion");
        Card unidad1 = new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>());

        cuerpoACuerpo.placeCard(unidad1);
        cuerpoACuerpo.placeCard(unidad2);
        cuerpoACuerpo.placeCard(unidad3);
        cuerpoACuerpo.placeCard(unidad4);
        cuerpoACuerpo.placeCard(unidad5);
        cuerpoACuerpo.placeCard(tierraArrasada);

        assertFalse(cuerpoACuerpo.getPlayedCards().contains(unidad4));
    }
}