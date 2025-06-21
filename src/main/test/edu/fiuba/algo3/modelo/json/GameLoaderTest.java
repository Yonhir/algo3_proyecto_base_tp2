package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.errors.GwentFileInvalid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GameLoaderTest {
    
    // Valid JSON files
    public static final String GWENT_JSON_PATH = "json/gwent.json";

    // Non existent JSON files
    public static final String NON_EXISTENT_GAME_PATH = "json/non_existent_game.json";

    // Empty JSON files
    public static final String EMPTY_JSON_PATH = "json/empty.json";
    
    // Invalid JSON files
    public static final String INVALID_GAME_JSON_PATH = "json/invalid_game.json";

    // Invalid structure JSON files
    public static final String INVALID_STRUCTURE_GAME_JSON_PATH = "json/invalid_structure_game.json";
    
    private GameLoader gameLoader;
    
    @BeforeEach
    void setUp() {
        gameLoader = new GameLoader();
    }

    // ============================================================================
    // GAME LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldLoadTwoDecks() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH);
        
        // Assert
        assertEquals(2, decks.size(), "Should load 2 decks from game JSON");
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveCards() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertFalse(playerOneDeck.getCards().isEmpty(), "Player one deck should have cards");
    }
    
    @Test
    void testLoadFromResource_PlayerTwoDeckShouldHaveCards() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertFalse(playerTwoDeck.getCards().isEmpty(), "Player two deck should have cards");
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveAtLeast15Units() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getUnitsCount() >= 15, "Player one deck should have at least 15 units");
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveAtLeast6Specials() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getSpecialsCount() >= 6, "Player one deck should have at least 6 specials");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(INVALID_STRUCTURE_GAME_JSON_PATH);
        }, "Should throw GwentFileInvalid when game file has invalid structure (missing required decks)");

        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    // ============================================================================
    // EXCEPTION TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileNotFound() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(NON_EXISTENT_GAME_PATH);
        }, "Should throw GwentFileInvalid when game file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(INVALID_GAME_JSON_PATH);
        }, "Should throw GwentFileInvalid when game file contains invalid JSON data types");

        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should indicate file reading or parsing error");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(EMPTY_JSON_PATH);
        }, "Should throw GwentFileInvalid when game file is empty");
        
        assertEquals("Error converting data", exception.getMessage(),
            "Exception message should indicate file reading or parsing error");
    }
} 