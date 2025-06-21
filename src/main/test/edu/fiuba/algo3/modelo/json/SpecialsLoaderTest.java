package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.specials.ScorchCard;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.specials.weathers.*;
import edu.fiuba.algo3.modelo.errors.SpecialsFileInvalid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SpecialsLoaderTest {
    
    // Valid JSON files
    public static final String ESPECIALES_JSON_PATH = "json/especiales.json";

    // Non existent JSON files
    public static final String NON_EXISTENT_SPECIALS_PATH = "json/non_existent_specials.json";

    // Empty JSON files
    public static final String EMPTY_JSON_PATH = "json/empty.json";
    
    // Invalid JSON files
    public static final String INVALID_SPECIALS_JSON_PATH = "json/invalid_specials.json";

    // Invalid structure JSON files
    public static final String INVALID_STRUCTURE_SPECIALS_JSON_PATH = "json/invalid_structure_specials.json";
    
    // Invalid weather name JSON files
    public static final String INVALID_WEATHER_NAME_JSON_PATH = "json/invalid_weather_name.json";
    
    private SpecialsLoader specialsLoader;
    
    @BeforeEach
    void setUp() {
        specialsLoader = new SpecialsLoader();
    }

    // ============================================================================
    // SPECIALS LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldNotBeEmpty() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        assertFalse(loadedSpecials.isEmpty(), "Should load at least one special from JSON");
    }
    
    @Test
    void testLoadFromResource_ShouldLoadAllSpecialCards() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        assertEquals(7, loadedSpecials.size(), "Should load exactly 7 special cards from JSON");
    }
    
    @Test
    void testLoadFromResource_ShouldLoadScorchCard() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundScorchCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof ScorchCard);
        assertTrue(foundScorchCard, "Should load Scorch card (Quemar) from JSON");
    }

    @Test
    void testLoadFromResource_ShouldLoadMoraleBoostCard() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundMoraleBoostCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof MoraleBoost);
        assertTrue(foundMoraleBoostCard, "Should load Morale Boost card (Cuerno del comandante) from JSON");
    }

    @Test
    void testLoadFromResource_ShouldLoadBitingFrostCard() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundBitingFrostCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof BitingFrost);
        assertTrue(foundBitingFrostCard, "Should load Biting Frost card (Escarcha mordaz) from JSON");
    }
    
    @Test
    void testLoadFromResource_ShouldLoadImpenetrableFogCard() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundImpenetrableFogCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof ImpenetrableFog);
        assertTrue(foundImpenetrableFogCard, "Should load Impenetrable Fog card (Niebla impenetrable) from JSON");
    }
    
    @Test
    void testLoadFromResource_ShouldLoadTorrentialRainCard() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundTorrentialRainCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof TorrentialRain);
        assertTrue(foundTorrentialRainCard, "Should load Torrential Rain card (Lluvia torrencial) from JSON");
    }
    
    @Test
    void testLoadFromResource_ShouldLoadClearWeatherCard() {
        // Act
        List<Special> loadedSpecials = specialsLoader.loadFromResource(ESPECIALES_JSON_PATH);
        
        // Assert
        boolean foundClearWeatherCard = loadedSpecials.stream()
                .anyMatch(special -> special instanceof ClearWeather);
        assertTrue(foundClearWeatherCard, "Should load Clear Weather card (Tiempo despejado) from JSON");
    }
    
    // ============================================================================
    // EXCEPTION TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldThrowSpecialsFileInvalid_WhenFileNotFound() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            specialsLoader.loadFromResource(NON_EXISTENT_SPECIALS_PATH);
        }, "Should throw SpecialsFileInvalid when specials file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should be 'Error reading or parsing file'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowSpecialsFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            specialsLoader.loadFromResource(INVALID_SPECIALS_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when specials file contains invalid special card types");
        
        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should be 'Error converting data'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowSpecialsFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            specialsLoader.loadFromResource(EMPTY_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when specials file is empty");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should be 'Error reading or parsing file'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowSpecialsFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            specialsLoader.loadFromResource(INVALID_STRUCTURE_SPECIALS_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when specials file has invalid structure (JSONObject instead of JSONArray)");
        
        assertEquals("Error reading or parsing file", exception.getMessage(),
            "Exception message should be 'Error reading or parsing file'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowSpecialsFileInvalid_WhenWeatherCardHasInvalidName() {
        // Act & Assert
        SpecialsFileInvalid exception = assertThrows(SpecialsFileInvalid.class, () -> {
            specialsLoader.loadFromResource(INVALID_WEATHER_NAME_JSON_PATH);
        }, "Should throw SpecialsFileInvalid when weather card has an invalid name");
        
        assertEquals("Error converting data", exception.getMessage(),
            "Exception message should be 'Error converting data'");
    }
} 