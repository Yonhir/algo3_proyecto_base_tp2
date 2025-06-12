package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScorchTest {
    private Row cuerpoACuerpo;
    private Row rango;
    private Row asedio;

    private Card unidad2;
    private Card unidad5;
    private Card unidad7;
    private Card unidad8;

    @BeforeEach
    void setUp() {
        cuerpoACuerpo = new CloseCombat();
        rango = new Ranged();
        asedio = new Siege();

        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
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
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaCloseCombatCorrectamente() {
        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo), List.of(rango), List.of(asedio));
        Card tierraArrasada = new Scorch("Nombre", "Descripcion");

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(cuerpoACuerpo.getPlayedCards().contains(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaRangedCorrectamente() {
        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo), List.of(rango), List.of(asedio));
        Card tierraArrasada = new Scorch("Nombre", "Descripcion");

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(rango.getPlayedCards().contains(unidad5));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaSiegeCorrectamente() {
        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo), List.of(rango), List.of(asedio));
        Card tierraArrasada = new Scorch("Nombre", "Descripcion");

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(rango.getPlayedCards().contains(unidad7) && rango.getPlayedCards().contains(unidad8));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaCloseCombatDelOponenteCorrectamente() {
        Row cuerpoACuerpo2 = new CloseCombat();
        Row rango2 = new Ranged();
        Row asedio2 = new Siege();

        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo, cuerpoACuerpo2), List.of(rango, rango2), List.of(asedio, asedio2));

        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        Card unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad9 = new Unit("Ice Giant", "Descripcion", 5, false, false, true, new ArrayList<Modifier>());
        Card tierraArrasada = new Scorch("Nombre", "Descripcion");

        cuerpoACuerpo2.placeCard(unidad1);
        cuerpoACuerpo2.placeCard(unidad2);
        cuerpoACuerpo2.placeCard(unidad3);
        rango2.placeCard(unidad4);
        rango2.placeCard(unidad5);
        rango2.placeCard(unidad6);
        asedio2.placeCard(unidad7);
        asedio2.placeCard(unidad8);
        asedio2.placeCard(unidad9);

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(cuerpoACuerpo2.getPlayedCards().contains(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaRangedDelOponenteCorrectamente() {
        Row cuerpoACuerpo2 = new CloseCombat();
        Row rango2 = new Ranged();
        Row asedio2 = new Siege();

        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo, cuerpoACuerpo2), List.of(rango, rango2), List.of(asedio, asedio2));

        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        Card unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad9 = new Unit("Ice Giant", "Descripcion", 5, false, false, true, new ArrayList<Modifier>());
        Card tierraArrasada = new Scorch("Nombre", "Descripcion");

        cuerpoACuerpo2.placeCard(unidad1);
        cuerpoACuerpo2.placeCard(unidad2);
        cuerpoACuerpo2.placeCard(unidad3);
        rango2.placeCard(unidad4);
        rango2.placeCard(unidad5);
        rango2.placeCard(unidad6);
        asedio2.placeCard(unidad7);
        asedio2.placeCard(unidad8);
        asedio2.placeCard(unidad9);

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(rango2.getPlayedCards().contains(unidad5));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaSiegeDelOponenteCorrectamente() {
        Row cuerpoACuerpo2 = new CloseCombat();
        Row rango2 = new Ranged();
        Row asedio2 = new Siege();

        SpecialZone zonaDeEspeciales = new SpecialZone(List.of(cuerpoACuerpo, cuerpoACuerpo2), List.of(rango, rango2), List.of(asedio, asedio2));

        Card unidad1 = new Unit("Griffin", "Descripcion", 5, true, false, false, new ArrayList<Modifier>());
        Card unidad2 = new Unit("Fiend", "Descripcion", 6, true, false, false, new ArrayList<Modifier>());
        Card unidad3 = new Unit("Donar an Hindar", "Descripcion", 4, true, false, false, new ArrayList<Modifier>());
        Card unidad4 = new Unit("Cynthia", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());
        Card unidad5 = new Unit("Dethmold", "Descripcion", 6, false, true, false, new ArrayList<Modifier>());
        Card unidad6 = new Unit("Endrega", "Descripcion", 2, false, true, false, new ArrayList<Modifier>());
        Card unidad7 = new Unit("Ballista", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad8 = new Unit("Earth Elemental", "Descripcion", 6, false, false, true, new ArrayList<Modifier>());
        Card unidad9 = new Unit("Ice Giant", "Descripcion", 5, false, false, true, new ArrayList<Modifier>());
        Card tierraArrasada = new Scorch("Nombre", "Descripcion");

        cuerpoACuerpo2.placeCard(unidad1);
        cuerpoACuerpo2.placeCard(unidad2);
        cuerpoACuerpo2.placeCard(unidad3);
        rango2.placeCard(unidad4);
        rango2.placeCard(unidad5);
        rango2.placeCard(unidad6);
        asedio2.placeCard(unidad7);
        asedio2.placeCard(unidad8);
        asedio2.placeCard(unidad9);

        zonaDeEspeciales.placeCard(tierraArrasada);

        assertFalse(asedio2.getPlayedCards().contains(unidad7) && asedio2.getPlayedCards().contains(unidad8));
    }
}