package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Agile;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Hero;
import edu.fiuba.algo3.modelo.errors.UnitsFileInvalid;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
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
        assertFalse(loadedUnits.isEmpty(), "Should load at least one unit from JSON");
    }

    @Test
    void testLoadFromResource_ShouldFindBarclayEls() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertNotNull(barclayEls, "Barclay Els unit should be found");
    }

    @Test
    void testLoadFromResource_BarclayElsShouldHaveCorrectPoints() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertEquals(6, barclayEls.calculatePoints(), "Barclay Els should have 6 points");
    }
    
    @Test
    void testLoadFromResource_BarclayElsShouldHaveAgileModifier() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertTrue(barclayEls.haveModifier(new Agile()), "Barclay Els should have Agile modifier");
    }
    
    @Test
    void testLoadFromResource_BarclayElsShouldBePlaceableInCloseCombat() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertDoesNotThrow(() -> barclayEls.verifySectionType(new CloseCombatType()),
            "Barclay Els should be placeable in Close Combat section");
    }
    
    @Test
    void testLoadFromResource_BarclayElsShouldBePlaceableInRanged() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertDoesNotThrow(() -> barclayEls.verifySectionType(new RangedType()),
            "Barclay Els should be placeable in Ranged section");
    }
    
    @Test
    void testLoadFromResource_ShouldFindBerserker() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertNotNull(berserker, "Berserker unit should be found");
    }
    
    @Test
    void testLoadFromResource_BerserkerShouldHaveCorrectPoints() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertEquals(4, berserker.calculatePoints(), "Berserker should have 4 points");
    }
    
    @Test
    void testLoadFromResource_BerserkerShouldNotHaveAgileModifier() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertFalse(berserker.haveModifier(new Agile()), "Berserker should not have Agile modifier");
    }
    
    @Test
    void testLoadFromResource_ShouldFindGeralt() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertNotNull(geralt, "Geralt de Rivia unit should be found");
    }
    
    @Test
    void testLoadFromResource_GeraltShouldHaveCorrectPoints() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertEquals(15, geralt.calculatePoints(), "Geralt should have 15 points");
    }
    
    @Test
    void testLoadFromResource_GeraltShouldHaveHeroModifier() {
        // Act
        loadedUnits = unitsLoader.loadFromResource(UNIDADES_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertTrue(geralt.haveModifier(new Hero()), "Geralt should have Hero modifier");
    }

    // ============================================================================
    // EXCEPTION TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileNotFound() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(NON_EXISTENT_UNITS_PATH, playerDeck, playerHand, playerDiscardPile);
        }, "Should throw UnitsFileInvalid when units file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should be 'Error reading or parsing file'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(INVALID_UNITS_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        }, "Should throw UnitsFileInvalid when units file contains invalid JSON data types");
        
        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should be 'Error converting data'");
    }

    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(EMPTY_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        }, "Should throw UnitsFileInvalid when units file is empty");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should be 'Error reading or parsing file'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowUnitsFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            unitsLoader.loadFromResource(INVALID_STRUCTURE_UNITS_JSON_PATH, playerDeck, playerHand, playerDiscardPile);
        }, "Should throw UnitsFileInvalid when units file has invalid structure (JSONObject instead of JSONArray)");
        
        assertEquals("Error reading or parsing file", exception.getMessage(),
            "Exception message should be 'Error reading or parsing file'");
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