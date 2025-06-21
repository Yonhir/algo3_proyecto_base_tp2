package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.specials.ScorchCard;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.specials.weathers.*;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Agile;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Hero;
import edu.fiuba.algo3.modelo.errors.GwentFileInvalid;
import edu.fiuba.algo3.modelo.errors.SpecialsFileInvalid;
import edu.fiuba.algo3.modelo.errors.UnitsFileInvalid;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class JsonCardLoaderTest {
    
    // Valid JSON files
    public static final String UNIDADES_JSON_PATH = "json/unidades.json";
    public static final String ESPECIALES_JSON_PATH = "json/especiales.json";
    public static final String GWENT_JSON_PATH = "json/gwent.json";

    // Non existent JSON files
    public static final String NON_EXISTENT_UNITS_PATH = "json/non_existent_units.json";
    public static final String NON_EXISTENT_SPECIALS_PATH = "json/non_existent_specials.json";
    public static final String NON_EXISTENT_GAME_PATH = "json/non_existent_game.json";

    // Empty JSON files
    public static final String EMPTY_JSON_PATH = "json/empty.json";
    
    // Invalid JSON files
    public static final String INVALID_UNITS_JSON_PATH = "json/invalid_units.json";
    public static final String INVALID_SPECIALS_JSON_PATH = "json/invalid_specials.json";
    public static final String INVALID_GAME_JSON_PATH = "json/invalid_game.json";

    // Invalid structure JSON files
    public static final String INVALID_STRUCTURE_UNITS_JSON_PATH = "json/invalid_structure_units.json";
    public static final String INVALID_STRUCTURE_SPECIALS_JSON_PATH = "json/invalid_structure_specials.json";
    public static final String INVALID_STRUCTURE_GAME_JSON_PATH = "json/invalid_structure_game.json";
    
    private JsonCardLoader jsonCardLoader;
    private List<Unit> loadedUnits;
    
    @BeforeEach
    void setUp() {
        jsonCardLoader = new JsonCardLoader();
        loadedUnits = new ArrayList<>();
    }

    // ============================================================================
    // UNIT LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadUnitsFromResource_ShouldNotBeEmpty() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);

        // Assert
        assertFalse(loadedUnits.isEmpty(), "Should load at least one unit from JSON");
    }

    @Test
    void testLoadUnitsFromResource_ShouldFindBarclayEls() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertNotNull(barclayEls, "Barclay Els unit should be found");
    }

    @Test
    void testLoadUnitsFromResource_BarclayElsShouldHaveCorrectPoints() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertEquals(6, barclayEls.calculatePoints(), "Barclay Els should have 6 points");
    }
    
    @Test
    void testLoadUnitsFromResource_BarclayElsShouldHaveAgileModifier() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertTrue(barclayEls.haveModifier(new Agile()), "Barclay Els should have Agile modifier");
    }
    
    @Test
    void testLoadUnitsFromResource_BarclayElsShouldBePlaceableInCloseCombat() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertDoesNotThrow(() -> barclayEls.verifySectionType(new CloseCombatType()),
            "Barclay Els should be placeable in Close Combat section");
    }
    
    @Test
    void testLoadUnitsFromResource_BarclayElsShouldBePlaceableInRanged() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit barclayEls = findUnitByName("Barclay Els");

        // Assert
        assertDoesNotThrow(() -> barclayEls.verifySectionType(new RangedType()),
            "Barclay Els should be placeable in Ranged section");
    }
    
    @Test
    void testLoadUnitsFromResource_ShouldFindBerserker() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertNotNull(berserker, "Berserker unit should be found");
    }
    
    @Test
    void testLoadUnitsFromResource_BerserkerShouldHaveCorrectPoints() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertEquals(4, berserker.calculatePoints(), "Berserker should have 4 points");
    }
    
    @Test
    void testLoadUnitsFromResource_BerserkerShouldNotHaveAgileModifier() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit berserker = findUnitByName("Berserker");

        // Assert
        assertFalse(berserker.haveModifier(new Agile()), "Berserker should not have Agile modifier");
    }
    
    @Test
    void testLoadUnitsFromResource_ShouldFindGeralt() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertNotNull(geralt, "Geralt de Rivia unit should be found");
    }
    
    @Test
    void testLoadUnitsFromResource_GeraltShouldHaveCorrectPoints() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertEquals(15, geralt.calculatePoints(), "Geralt should have 15 points");
    }
    
    @Test
    void testLoadUnitsFromResource_GeraltShouldHaveHeroModifier() {
        // Act
        loadedUnits = jsonCardLoader.loadUnitsFromResource(UNIDADES_JSON_PATH);
        Unit geralt = findUnitByName("Geralt de Rivia");

        // Assert
        assertTrue(geralt.haveModifier(new Hero()), "Geralt should have Hero modifier");
    }

    // ============================================================================
    // SPECIALS LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadSpecialsFromResource_ShouldNotBeEmpty() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        assertFalse(loadedSpecials.isEmpty(), "Should load at least one special from JSON");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldLoadAllSpecialCards() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        assertEquals(7, loadedSpecials.size(), "Should load exactly 7 special cards from JSON");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldLoadScorchCard() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundScorchCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof ScorchCard);
        assertTrue(foundScorchCard, "Should load Scorch card (Quemar) from JSON");
    }

    @Test
    void testLoadSpecialsFromResource_ShouldLoadMoraleBoostCard() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundMoraleBoostCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof MoraleBoost);
        assertTrue(foundMoraleBoostCard, "Should load Morale Boost card (Cuerno del comandante) from JSON");
    }

    @Test
    void testLoadSpecialsFromResource_ShouldLoadBitingFrostCard() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundBitingFrostCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof BitingFrost);
        assertTrue(foundBitingFrostCard, "Should load Biting Frost card (Escarcha mordaz) from JSON");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldLoadImpenetrableFogCard() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundImpenetrableFogCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof ImpenetrableFog);
        assertTrue(foundImpenetrableFogCard, "Should load Impenetrable Fog card (Niebla impenetrable) from JSON");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldLoadTorrentialRainCard() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundTorrentialRainCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof TorrentialRain);
        assertTrue(foundTorrentialRainCard, "Should load Torrential Rain card (Lluvia torrencial) from JSON");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldLoadClearWeatherCard() {
        // Act
        List<Special> loadedSpecials = jsonCardLoader.loadSpecialsFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundClearWeatherCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof ClearWeather);
        assertTrue(foundClearWeatherCard, "Should load Clear Weather card (Tiempo despejado) from JSON");
    }
    
    // ============================================================================
    // GAME LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadGameFromResource_ShouldLoadTwoDecks() {
        // Act
        List<Deck> decks = jsonCardLoader.loadGameFromResource(GWENT_JSON_PATH);
        
        // Assert
        assertEquals(2, decks.size(), "Should load 2 decks from game JSON");
    }
    
    @Test
    void testLoadGameFromResource_PlayerOneDeckShouldHaveCards() {
        // Act
        List<Deck> decks = jsonCardLoader.loadGameFromResource(GWENT_JSON_PATH);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertFalse(playerOneDeck.getCards().isEmpty(), "Player one deck should have cards");
    }
    
    @Test
    void testLoadGameFromResource_PlayerTwoDeckShouldHaveCards() {
        // Act
        List<Deck> decks = jsonCardLoader.loadGameFromResource(GWENT_JSON_PATH);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertFalse(playerTwoDeck.getCards().isEmpty(), "Player two deck should have cards");
    }
    
    @Test
    void testLoadGameFromResource_PlayerOneDeckShouldHaveAtLeast15Units() {
        // Act
        List<Deck> decks = jsonCardLoader.loadGameFromResource(GWENT_JSON_PATH);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getUnitsCount() >= 15, "Player one deck should have at least 15 units");
    }
    
    @Test
    void testLoadGameFromResource_PlayerOneDeckShouldHaveAtLeast6Specials() {
        // Act
        List<Deck> decks = jsonCardLoader.loadGameFromResource(GWENT_JSON_PATH);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getSpecialsCount() >= 6, "Player one deck should have at least 6 specials");
    }
    
    @Test
    void testLoadGameFromResource_ShouldThrowGwentFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            jsonCardLoader.loadGameFromResource(INVALID_STRUCTURE_GAME_JSON_PATH);
        }, "Should throw GwentFileInvalid when game file has invalid structure (missing required decks)");

        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    // ============================================================================
    // EXCEPTION TESTS
    // ============================================================================
    
    @Test
    void testLoadUnitsFromResource_ShouldThrowUnitsFileInvalid_WhenFileNotFound() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            jsonCardLoader.loadUnitsFromResource(NON_EXISTENT_UNITS_PATH);
        }, "Should throw UnitsFileInvalid when units file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadUnitsFromResource_ShouldThrowUnitsFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            jsonCardLoader.loadUnitsFromResource(INVALID_UNITS_JSON_PATH);
        }, "Should throw UnitsFileInvalid when units file contains invalid JSON data types");
        
        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldThrowSpecialsFileInvalid_WhenFileNotFound() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            jsonCardLoader.loadSpecialsFromResource(NON_EXISTENT_SPECIALS_PATH);
        }, "Should throw SpecialsFileInvalid when specials file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldThrowSpecialsFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            jsonCardLoader.loadSpecialsFromResource(INVALID_SPECIALS_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when specials file contains invalid special card types");
        
        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should indicate data conversion error");
    }
    
    @Test
    void testLoadGameFromResource_ShouldThrowGwentFileInvalid_WhenFileNotFound() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            jsonCardLoader.loadGameFromResource(NON_EXISTENT_GAME_PATH);
        }, "Should throw GwentFileInvalid when game file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadGameFromResource_ShouldThrowGwentFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            jsonCardLoader.loadGameFromResource(INVALID_GAME_JSON_PATH);
        }, "Should throw GwentFileInvalid when game file contains invalid JSON data types");

        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }

    @Test
    void testLoadUnitsFromResource_ShouldThrowUnitsFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            jsonCardLoader.loadUnitsFromResource(EMPTY_JSON_PATH);
        }, "Should throw UnitsFileInvalid when units file is empty");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldThrowSpecialsFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            jsonCardLoader.loadSpecialsFromResource(EMPTY_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when specials file is empty");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadGameFromResource_ShouldThrowGwentFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            jsonCardLoader.loadGameFromResource(EMPTY_JSON_PATH);
        }, "Should throw GwentFileInvalid when game file is empty");
        
        assertEquals("Error converting data", exception.getMessage(),
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadUnitsFromResource_ShouldThrowUnitsFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        UnitsFileInvalid exception = assertThrows(UnitsFileInvalid.class, () -> {
            jsonCardLoader.loadUnitsFromResource(INVALID_STRUCTURE_UNITS_JSON_PATH);
        }, "Should throw UnitsFileInvalid when units file has invalid structure (JSONObject instead of JSONArray)");
        
        assertEquals("Error reading or parsing file", exception.getMessage(),
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadSpecialsFromResource_ShouldThrowSpecialsFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            jsonCardLoader.loadSpecialsFromResource(INVALID_STRUCTURE_SPECIALS_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when specials file has invalid structure (JSONObject instead of JSONArray)");
        
        assertEquals("Error reading or parsing file", exception.getMessage(),
            "Exception message should indicate file reading or parsing error");
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