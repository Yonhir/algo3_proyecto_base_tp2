package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.GameController;
import edu.fiuba.algo3.controllers.GameSetupController;
import edu.fiuba.algo3.controllers.MainMenuController;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.colors.Blue;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.colors.Red;
import edu.fiuba.algo3.modelo.errors.GwentFileInvalid;
import edu.fiuba.algo3.modelo.json.GameLoader;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.turnManagement.Game;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.vistas.components.ErrorDialog;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ViewManager {
    public static final String GWENT_JSON_PATH = "gwent.json";

    private final Stage stage;
    private Scene currentScene;

    private boolean isFullScreen = false;

    private MainMenuView mainMenuView;
    private GameView gameView;

    public Deck redPlayerDeck;
    public Deck bluePlayerDeck;
    public DiscardPile redPlayerDiscardPile;
    public DiscardPile bluePlayerDiscardPile;
    public Hand redPlayerHand;
    public Hand bluePlayerHand;
    public CloseCombat redCloseCombat;
    public Ranged redRanged;
    public Siege redSiege;
    public CloseCombat blueCloseCombat;
    public Ranged blueRanged;
    public Siege blueSiege;
    public SpecialZone specialZone;
    public Player redPlayer;
    public Player bluePlayer;
    public Game gameModel;
    


    public ViewManager(Stage stage) {
        this.stage = stage;
    }

    public void showMainMenu() {
        mainMenuView = new MainMenuView();
        gameView = null;

        MainMenuController controller = new MainMenuController(this, mainMenuView);
        mainMenuView.setController(controller);

        currentScene = mainMenuView.createScene();

        stage.setScene(currentScene);
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }

    public void startGame() {
        PlayerColor redColor = new Red();
        redPlayerDeck = new Deck();
        redPlayerDiscardPile = new DiscardPile();
        redCloseCombat = new CloseCombat(redPlayerDiscardPile);
        redRanged = new Ranged(redPlayerDiscardPile);
        redSiege = new Siege(redPlayerDiscardPile);

        PlayerColor blueColor = new Blue();
        bluePlayerDeck = new Deck();
        bluePlayerDiscardPile = new DiscardPile();
        blueCloseCombat = new CloseCombat(bluePlayerDiscardPile);
        blueRanged = new Ranged(bluePlayerDiscardPile);
        blueSiege = new Siege(bluePlayerDiscardPile);

        specialZone = new SpecialZone(
                redCloseCombat, redRanged, redSiege,
                blueCloseCombat, blueRanged, blueSiege,
                redPlayerDiscardPile, bluePlayerDiscardPile
        );

        redPlayer = new Player(
                "redPlayer", redPlayerDeck, redPlayerDiscardPile,
                redCloseCombat, redRanged, redSiege, redColor
        );
        
        bluePlayer = new Player(
            "bluePlayer", bluePlayerDeck, bluePlayerDiscardPile,
            blueCloseCombat, blueRanged, blueSiege, blueColor
            );

        redPlayerHand = redPlayer.getHand();
        bluePlayerHand = bluePlayer.getHand();

        GameLoader gameLoader = new GameLoader();

        try {
            gameLoader.loadFromResource(GWENT_JSON_PATH,
                redPlayerDeck, redPlayerHand, redPlayerDiscardPile,
                bluePlayerDeck, bluePlayerHand, bluePlayerDiscardPile
            );
        } catch (GwentFileInvalid e) {
            if (mainMenuView != null) {
                ErrorDialog.show(
                    mainMenuView, "Game Loading Error", 
                    "Failed to load game configuration from file");
            } else {
                System.err.println("Error loading game: " + e.getMessage());
                showMainMenu();
            }
            return;
        }
 
        showGameSetupView();
    }
    
    private void showGameSetupView() {

        GameSetupView gameSetupView = new GameSetupView(redPlayerHand, bluePlayerHand, this);
        
        // Create and set up the controller
        GameSetupController controller = new GameSetupController(
            this, gameSetupView,
            redPlayerHand, bluePlayerHand,
            redPlayerDeck, bluePlayerDeck,
            redPlayerDiscardPile, bluePlayerDiscardPile
        );

        mainMenuView = null;
        gameView = null;

        currentScene = gameSetupView.createScene();
        stage.setScene(currentScene);
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }
    
    public void showGameView() {
        gameModel = new Game(redPlayer, bluePlayer, specialZone);
        
        gameView = new GameView();
        mainMenuView = null;

        GameController controller = new GameController(this, gameView);

        currentScene = gameView.createScene();

        stage.setScene(currentScene);
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }

    public void toggleFullScreen() {
        isFullScreen = !isFullScreen;
        stage.setFullScreen(isFullScreen);
    }

    public void showExitConfirmation() {
        if (mainMenuView != null) {
            mainMenuView.showExitConfirmation();
        } else if (gameView != null) {
            gameView.showExitConfirmation();
        }
    }
}
