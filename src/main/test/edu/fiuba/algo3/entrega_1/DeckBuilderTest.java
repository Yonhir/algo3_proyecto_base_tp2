package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DeckBuilderTest {
    @Test
    public void testElJugadorPoseeCartasTotalesSuficientesEnSuMazoParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoCartas = 21;

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        List<Card> seleccionadas = constructor.getSelection();

        assertTrue(seleccionadas.size() >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoEspeciales = 6;

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        List<Card> especialesSeleccionadas = constructor.getSpecialsSelected();

        assertTrue(especialesSeleccionadas.size() >= minimoEspeciales);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesParaComenzarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoUnidades = 15;

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        List<Card> unidadesSeleccionadas = constructor.getUnitsSelected();

        assertTrue(unidadesSeleccionadas.size() >= minimoUnidades);
    }

    @Test
    public void testElMazoSeCreaCorrectamenteConLasSuficientesCartasParaComenzarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoCartas = 21;

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getCardCount() >= minimoCartas);
    }

    @Test
    public void testElMazoSeCreaConSuficientesCartasUnidadesParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoUnidades = 15;

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElMazoSeCreaConSuficientesCartasEspecialesParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoEspeciales = 6;

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSePuedeCrearElMazoSiNoHaySuficientesCartasUnidades() {
        DeckBuilder constructor = new DeckBuilder();

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Scorch("Scorch", ""));
        constructor.selectCard(new CommandersHorn("Commander's Horn", ""));
        constructor.selectCard(new BitingFrost("Biting Frost", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));
        constructor.selectCard(new TorrentialRain("Torrential Rain", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));

        assertThrows(IllegalArgumentException.class, constructor::buildDeck);
    }

    @Test
    public void testNoSePuedeCrearElMazoSiNoHaySuficientesCartasEspeciales() {
        DeckBuilder constructor = new DeckBuilder();

        constructor.selectCard(new Decoy("Decoy", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));
        constructor.selectCard(new Mardroeme("Mardroeme", ""));

        constructor.selectCard(new Unit("Botchling", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Berserker", "", 4, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Dandelion", "", 2, new CloseCombat()));
        constructor.selectCard(new Unit("Griffin", "", 5, new CloseCombat()));
        constructor.selectCard(new Unit("Cerys", "", 10, new CloseCombat()));
        constructor.selectCard(new Unit("Cow", "", 0, new Ranged()));
        constructor.selectCard(new Unit("Dethmold", "", 6, new Ranged()));
        constructor.selectCard(new Unit("Harpy", "", 2, new Ranged()));
        constructor.selectCard(new Unit("Toad", "", 7, new Ranged()));
        constructor.selectCard(new Unit("Cynthia", "", 4, new Ranged()));
        constructor.selectCard(new Unit("Ermion", "", 8, new Ranged()));
        constructor.selectCard(new Unit("Ballista", "", 6, new Siege()));
        constructor.selectCard(new Unit("Catapult", "", 8, new Siege()));
        constructor.selectCard(new Unit("Thaler", "", 1, new Siege()));
        constructor.selectCard(new Unit("Ice Giant", "", 5, new Siege()));

        assertThrows(IllegalArgumentException.class, constructor::buildDeck);
    }
}