package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
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
    private Deck player1Deck;
    private Hand player1Hand;
    private DiscardPile player1DiscardPile;
    private Deck player2Deck;
    private Hand player2Hand;
    private DiscardPile player2DiscardPile;
    
    @BeforeEach
    void setUp() {
        gameLoader = new GameLoader();
        
        // Create dependencies for both players
        player1Deck = new Deck();
        player1Hand = new Hand();
        player1DiscardPile = new DiscardPile();
        
        player2Deck = new Deck();
        player2Hand = new Hand();
        player2DiscardPile = new DiscardPile();
    }

    // ============================================================================
    // GAME LOADING TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldLoadTwoDecks() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        
        // Assert
        assertEquals(2, decks.size(), "Should load 2 decks from game JSON");
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveCards() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertFalse(playerOneDeck.getCards().isEmpty(), "Player one deck should have cards");
    }
    
    @Test
    void testLoadFromResource_PlayerTwoDeckShouldHaveCards() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertFalse(playerTwoDeck.getCards().isEmpty(), "Player two deck should have cards");
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveAtLeast15Units() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getUnitsCount() >= 15, "Player one deck should have at least 15 units");
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveAtLeast6Specials() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getSpecialsCount() >= 6, "Player one deck should have at least 6 specials");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(INVALID_STRUCTURE_GAME_JSON_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        }, "Should throw GwentFileInvalid when game file has invalid structure (missing required decks)");

        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should be 'Error converting data'");
    }
    
    // ============================================================================
    // EXCEPTION TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileNotFound() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(NON_EXISTENT_GAME_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        }, "Should throw GwentFileInvalid when game file is not found");
        
        assertEquals("Error reading or parsing file", exception.getMessage(), 
            "Exception message should be 'Error reading or parsing file'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(INVALID_GAME_JSON_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        }, "Should throw GwentFileInvalid when game file contains invalid JSON data types");

        assertEquals("Error converting data", exception.getMessage(), 
            "Exception message should be 'Error converting data'");
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(EMPTY_JSON_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        }, "Should throw GwentFileInvalid when game file is empty");
        
        assertEquals("Error converting data", exception.getMessage(),
            "Exception message should be 'Error converting data'");
    }
    
    // ============================================================================
    // NULL DECK HANDLING TESTS
    // ============================================================================
    
    @Test
    void testLoadFromResource_ShouldCreateNewDeckWhenPlayerOneDeckIsNull() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      null, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertNotNull(playerOneDeck, "Should create new deck when player one deck is null");
        assertFalse(playerOneDeck.getCards().isEmpty(), "Created deck should have cards");
        assertNotSame(player1Deck, playerOneDeck, "Should be a different deck instance");
    }
    
    @Test
    void testLoadFromResource_ShouldCreateNewDeckWhenPlayerTwoDeckIsNull() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      null, player2Hand, player2DiscardPile);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertNotNull(playerTwoDeck, "Should create new deck when player two deck is null");
        assertFalse(playerTwoDeck.getCards().isEmpty(), "Created deck should have cards");
        assertNotSame(player2Deck, playerTwoDeck, "Should be a different deck instance");
    }
    
    @Test
    void testLoadFromResource_ShouldCreateNewDecksWhenBothDecksAreNull() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      null, player1Hand, player1DiscardPile,
                                                      null, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertNotNull(playerOneDeck, "Should create new deck when player one deck is null");
        assertNotNull(playerTwoDeck, "Should create new deck when player two deck is null");
        assertFalse(playerOneDeck.getCards().isEmpty(), "Player one created deck should have cards");
        assertFalse(playerTwoDeck.getCards().isEmpty(), "Player two created deck should have cards");
        assertNotSame(playerOneDeck, playerTwoDeck, "Should be different deck instances");
    }
}
