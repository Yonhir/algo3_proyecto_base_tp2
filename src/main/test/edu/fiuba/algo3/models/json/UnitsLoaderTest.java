package edu.fiuba.algo3.models.json;

import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.cards.units.modifiers.Agile;
import edu.fiuba.algo3.models.cards.units.modifiers.Hero;
import edu.fiuba.algo3.models.errors.UnitsFileInvalid;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UnitsLoaderTest {
    
    // Valid JSON files
    public static final String UNIDADES_JSON_PATH = "json/unidades.json";

    // Non existent JSON files
    public static final String NON_EXISTENT_UNITS_PATH = "json/non_existent_units.json";

    // Empty JSON files
    public static final String EMPTY_JSON_PATH = "json/empty.json";
    
    // Invalid JSON files
    public static final String INVALID_UNITS_JSON_PATH = "json/invalid_units.json";

    // Invalid structure JSON files
    public static final String INVALID_STRUCTURE_UNITS_JSON_PATH = "json/invalid_structure_units.json";
    
    private UnitsLoader unitsLoader;
    private List<Unit> loadedUnits;
    private Deck playerDeck;
    private Hand playerHand;
    private DiscardPile playerDiscardPile;
    
    @BeforeEach
    void setUp() {
        unitsLoader = new UnitsLoader();
        loadedUnits = new ArrayList<>();
        playerDeck = new Deck();
        playerHand = new Hand();
        playerDiscardPile = new DiscardPile();
    }

    // ============================================================================
    // UNIT LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldNotBeEmpty() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);

        // Assert
        assertFalse(loadedUnits.isEmpty());
    }

    @Test
    void testLoadFromResource_ShouldFindBarclayEls() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertNotNull(barclayEls);
    }

    @Test
    void testLoadFromResource_BarclayElsShouldHaveCorrectPoints() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertEquals(6, barclayEls.calculatePoints());
    }
    
    @Test
    void testLoadFromResource_BarclayElsShouldHaveAgileModifier() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertTrue(barclayEls.haveModifier(new Agile()));
    }
    
    @Test
    void testLoadFromResource_BarclayElsShouldBePlaceableInCloseCombat() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertDoesNotThrow(() -> barclayEls.verifySectionType(new CloseCombatType()));
    }
    
    @Test
    void testLoadFromResource_BarclayElsShouldBePlaceableInRanged() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertDoesNotThrow(() -> barclayEls.verifySectionType(new RangedType()));
    }
    
    @Test
    void testLoadFromResource_ShouldFindBerserker() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertNotNull(berserker);
    }
    
    @Test
    void testLoadFromResource_BerserkerShouldHaveCorrectPoints() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertEquals(4, berserker.calculatePoints());
    }
    
    @Test
    void testLoadFromResource_BerserkerShouldNotHaveAgileModifier() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertFalse(berserker.haveModifier(new Agile()));
    }
    
    @Test
    void testLoadFromResource_ShouldFindGeralt() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertNotNull(geralt);
    }
    
    @Test
    void testLoadFromResource_GeraltShouldHaveCorrectPoints() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertEquals(15, geralt.calculatePoints());
    }
    
    @Test
    void testLoadFromResource_GeraltShouldHaveHeroModifier() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertTrue(geralt.haveModifier(new Hero()));
    }

    // ============================================================================
    // EXCEPTION TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileNotFound() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(NON_EXISTENT_UNITS_PATH, playerDeck, playerHand, playerDiscardPile);
        });
        
        assertEquals("Error reading or parsing file", exception.getMessage());
    }
    
    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(INVALID_UNITS_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        });
        
        assertEquals("Error converting data", exception.getMessage());
    }

    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(EMPTY_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        });
        
        assertEquals("Error reading or parsing file", exception.getMessage());
    }
    
    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(INVALID_STRUCTURE_UNITS_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        });
        
        assertEquals("Error reading or parsing file", exception.getMessage());
    }
    
    // ============================================================================
    // HELPER METHODS
    // ============================================================================
    
    private Unit findUnitByName(String name) {
        return loadedUnits.stream()
                .filter(unit -> name.equals(unit.getName()))
                .findFirst()
                .orElse(null);
    }
} 