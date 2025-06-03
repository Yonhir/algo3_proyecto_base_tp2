package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckBuilderTest {
    @Test
    public void testElJugadorPoseeCartasTotalesSuficientesEnSuMazoParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoCartas = 21;

        Special especial1 = new Decoy("Decoy", "");
        Special especial2 = new Mardroeme("Mardroeme", "");
        Special especial3 = new Scorch("Scorch", "");
        Special especial4 = new CommandersHorn("Commander's Horn", "");
        Weather especial5 = new BitingFrost("Biting Frost", "");
        Weather especial6 = new TorrentialRain("Torrential Rain", "");

        constructor.selectCard(especial1);
        constructor.selectCard(especial2);
        constructor.selectCard(especial3);
        constructor.selectCard(especial4);
        constructor.selectCard(especial5);
        constructor.selectCard(especial6);

        Card unidad1 = new Unit("Botchling", "", 4, new CloseCombat());
        Card unidad2 = new Unit("Berserker", "", 4, new CloseCombat());
        Card unidad3 = new Unit("Dandelion", "", 2, new CloseCombat());
        Card unidad4 = new Unit("Griffin", "", 5, new CloseCombat());
        Card unidad5 = new Unit("Cerys", "", 10, new CloseCombat());
        Card unidad6 = new Unit("Cow", "", 0, new Ranged());
        Card unidad7 = new Unit("Dethmold", "", 6, new Ranged());
        Card unidad8 = new Unit("Harpy", "", 2, new Ranged());
        Card unidad9 = new Unit("Toad", "", 7, new Ranged());
        Card unidad10 = new Unit("Cynthia", "", 4, new Ranged());
        Card unidad11 = new Unit("Ermion", "", 8, new Ranged());
        Card unidad12 = new Unit("Ballista", "", 6, new Siege());
        Card unidad13 = new Unit("Catapult", "", 8, new Siege());
        Card unidad14 = new Unit("Thaler", "", 1, new Siege());
        Card unidad15 = new Unit("Ice Giant", "", 5, new Siege());

        constructor.selectCard(unidad1);
        constructor.selectCard(unidad2);
        constructor.selectCard(unidad3);
        constructor.selectCard(unidad4);
        constructor.selectCard(unidad5);
        constructor.selectCard(unidad6);
        constructor.selectCard(unidad7);
        constructor.selectCard(unidad8);
        constructor.selectCard(unidad9);
        constructor.selectCard(unidad10);
        constructor.selectCard(unidad11);
        constructor.selectCard(unidad12);
        constructor.selectCard(unidad13);
        constructor.selectCard(unidad14);
        constructor.selectCard(unidad15);

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getCardCount() >= minimoCartas);
    }
}