package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScorchTest {
    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaCloseCombatCorrectamente() {
        Row cuerpoACuerpo = new CloseCombat();
        Row rango = new Ranged();
        Row asedio = new Siege();
        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo), List.of(rango), List.of(asedio));

        Card tierraArrasada = new Scorch("Nombre", "Descripcion");
        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        Card unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad9 = new Unit("Ice Giant", "Descripcion", 5, false, false, true, new ArrayList<Modifier>());

        cuerpoACuerpo.placeCard(unidad1);
        cuerpoACuerpo.placeCard(unidad2);
        cuerpoACuerpo.placeCard(unidad3);
        rango.placeCard(unidad4);
        rango.placeCard(unidad5);
        rango.placeCard(unidad6);
        asedio.placeCard(unidad7);
        asedio.placeCard(unidad8);
        asedio.placeCard(unidad9);

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(cuerpoACuerpo.getPlayedCards().contains(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaRangedCorrectamente() {
        Row cuerpoACuerpo = new CloseCombat();
        Row rango = new Ranged();
        Row asedio = new Siege();
        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo), List.of(rango), List.of(asedio));

        Card tierraArrasada = new Scorch("Nombre", "Descripcion");
        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        Card unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad9 = new Unit("Ice Giant", "Descripcion", 5, false, false, true, new ArrayList<Modifier>());

        cuerpoACuerpo.placeCard(unidad1);
        cuerpoACuerpo.placeCard(unidad2);
        cuerpoACuerpo.placeCard(unidad3);
        rango.placeCard(unidad4);
        rango.placeCard(unidad5);
        rango.placeCard(unidad6);
        asedio.placeCard(unidad7);
        asedio.placeCard(unidad8);
        asedio.placeCard(unidad9);

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(rango.getPlayedCards().contains(unidad5));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaSiegeCorrectamente() {
        Row cuerpoACuerpo = new CloseCombat();
        Row rango = new Ranged();
        Row asedio = new Siege();
        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo), List.of(rango), List.of(asedio));

        Card tierraArrasada = new Scorch("Nombre", "Descripcion");
        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        Card unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad9 = new Unit("Ice Giant", "Descripcion", 5, false, false, true, new ArrayList<Modifier>());

        cuerpoACuerpo.placeCard(unidad1);
        cuerpoACuerpo.placeCard(unidad2);
        cuerpoACuerpo.placeCard(unidad3);
        rango.placeCard(unidad4);
        rango.placeCard(unidad5);
        rango.placeCard(unidad6);
        asedio.placeCard(unidad7);
        asedio.placeCard(unidad8);
        asedio.placeCard(unidad9);

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(rango.getPlayedCards().contains(unidad7) && rango.getPlayedCards().contains(unidad8));
    }
}