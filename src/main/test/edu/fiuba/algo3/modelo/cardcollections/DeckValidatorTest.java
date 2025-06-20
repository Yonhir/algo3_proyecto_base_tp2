package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.cards.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.NotEnoughSpecialsCardsError;
import edu.fiuba.algo3.modelo.errors.NotEnoughUnitsCardsError;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckValidatorTest {
    @Test
    public void testSeValidaCorrectamenteElMinimoDeCartasUnidades() {
        Validate15UnitsCards validador = new Validate15UnitsCards();
        List<Card> unidades = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<Modifier>())
        );

        assertDoesNotThrow(() -> validador.validate(unidades));
    }

    @Test
    public void testSeLanzaExcepcionSiLaCantidadDeCartasUnidadesNoSuperaElMinimo() {
        Validate15UnitsCards validador = new Validate15UnitsCards();
        List<Card> unidades = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<Modifier>())
        );

        assertThrows(NotEnoughUnitsCardsError.class, () -> validador.validate(unidades));
    }

    @Test
    public void testSeValidaCorrectamenteSiLaCantidadDeCartasUnidadesSuperaElMinimo() {
        Validate15UnitsCards validador = new Validate15UnitsCards();
        List<Card> unidades = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<Modifier>())
        );

        assertDoesNotThrow(() -> validador.validate(unidades));
    }

    @Test
    public void testSeLanzaExcepcionSiLaCantidadDeCartasUnidadesEsNula() {
        Validate15UnitsCards validador = new Validate15UnitsCards();
        List<Card> unidades = new ArrayList<>();

        assertThrows(NotEnoughUnitsCardsError.class, () -> validador.validate(unidades));
    }

    @Test
    public void testSeValidaCorrectamenteElMinimoDeCartasEspeciales() {
        Validate6SpecialCards validador = new Validate6SpecialCards();
        List<Card> especiales = Arrays.asList(
                new TorrentialRain("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion")
        );

        assertDoesNotThrow(() -> validador.validate(especiales));
    }

    @Test
    public void testSeLanzaExcepcionSiLaCantidadDeCartasEspecialesNoSuperaLaMinima() {
        Validate6SpecialCards validador = new Validate6SpecialCards();
        List<Card> especiales = Arrays.asList(
                new TorrentialRain("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion")
        );

        assertThrows(NotEnoughSpecialsCardsError.class, () -> validador.validate(especiales));
    }

    @Test
    public void testSeValidaCorrectamenteSiLaCantidadDeCartasEspecialesSuperaElMinimo() {
        Validate6SpecialCards validador = new Validate6SpecialCards();
        List<Card> especiales = Arrays.asList(
                new TorrentialRain("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion")
        );

        assertDoesNotThrow(() -> validador.validate(especiales));
    }

    @Test
    public void testSeLanzaExcepcionSiLaCantidadDeCartasEspecialesEsNula() {
        Validate6SpecialCards validador = new Validate6SpecialCards();
        List<Card> especiales = new ArrayList<>();

        assertThrows(NotEnoughSpecialsCardsError.class, () -> validador.validate(especiales));
    }
}
