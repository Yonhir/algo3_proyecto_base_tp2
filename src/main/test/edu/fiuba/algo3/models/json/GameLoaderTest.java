package edu.fiuba.algo3.models.json;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.errors.GwentFileInvalid;
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
        assertEquals(2, decks.size());
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveCards() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertFalse(playerOneDeck.getCards().isEmpty());
    }
    
    @Test
    void testLoadFromResource_PlayerTwoDeckShouldHaveCards() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertFalse(playerTwoDeck.getCards().isEmpty());
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveAtLeast15Units() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getUnitsCount() >= 15);
    }
    
    @Test
    void testLoadFromResource_PlayerOneDeckShouldHaveAtLeast6Specials() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      player2Deck, player2Hand, player2DiscardPile);
        Deck playerOneDeck = decks.get(0);
        
        // Assert
        assertTrue(playerOneDeck.getSpecialsCount() >= 6);
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileHasInvalidStructure() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(INVALID_STRUCTURE_GAME_JSON_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        });

        assertEquals("Error converting data", exception.getMessage());
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
        });
        
        assertEquals("Error reading or parsing file", exception.getMessage());
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileIsInvalidJson() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(INVALID_GAME_JSON_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        });

        assertEquals("Error converting data", exception.getMessage());
    }
    
    @Test
    void testLoadFromResource_ShouldThrowGwentFileInvalid_WhenFileIsEmpty() {
        // Act & Assert
        GwentFileInvalid exception = assertThrows(GwentFileInvalid.class, () -> {
            gameLoader.loadFromResource(EMPTY_JSON_PATH, 
                                      player1Deck, player1Hand, player1DiscardPile,
                                      player2Deck, player2Hand, player2DiscardPile);
        });
        
        assertEquals("Error converting data", exception.getMessage());
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
        assertNotNull(playerOneDeck);
        assertNotSame(player1Deck, playerOneDeck);
    }
    
    @Test
    void testLoadFromResource_ShouldCreateNewDeckWhenPlayerTwoDeckIsNull() {
        // Act
        List<Deck> decks = gameLoader.loadFromResource(GWENT_JSON_PATH, 
                                                      player1Deck, player1Hand, player1DiscardPile,
                                                      null, player2Hand, player2DiscardPile);
        Deck playerTwoDeck = decks.get(1);
        
        // Assert
        assertNotNull(playerTwoDeck);
        assertNotSame(player2Deck, playerTwoDeck);
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
        assertNotNull(playerOneDeck);
        assertNotNull(playerTwoDeck);
        assertNotSame(playerOneDeck, playerTwoDeck);
    }
}
