package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Unit;
import edu.fiuba.algo3.modelo.Modifier;
import edu.fiuba.algo3.modelo.Agil;
import edu.fiuba.algo3.modelo.TightBond;
import edu.fiuba.algo3.vistas.PlayerProfileManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private ArrayList<Unit> createdCards = new ArrayList<>();
    private PlayersManager playersManager;

    @Override
    public void start(Stage stage) {
        // Initialize the players manager
        playersManager = new PlayersManager();
        
        // Create the main container
        BorderPane mainContainer = new BorderPane();
        
        // Create game-style menu with buttons in the center
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(50));
        
        // Game title
        Label titleLabel = new Label("Juego de Cartas");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");
        
        // Create menu buttons
        Button createCardsButton = new Button("Crear Cartas");
        createCardsButton.setPrefWidth(200);
        createCardsButton.setPrefHeight(50);
        createCardsButton.setStyle("-fx-font-size: 16px;");
        
        Button buildDeckButton = new Button("Construir Mazo");
        buildDeckButton.setPrefWidth(200);
        buildDeckButton.setPrefHeight(50);
        buildDeckButton.setStyle("-fx-font-size: 16px;");
        
        Button managePlayers = new Button("Gestionar Jugadores");
        managePlayers.setPrefWidth(200);
        managePlayers.setPrefHeight(50);
        managePlayers.setStyle("-fx-font-size: 16px;");
        
        // Add more buttons for future features
        Button playGameButton = new Button("Jugar");
        playGameButton.setPrefWidth(200);
        playGameButton.setPrefHeight(50);
        playGameButton.setStyle("-fx-font-size: 16px;");
        // playGameButton.setDisable(true); // Enable the button

        Button exportPlayersButton = new Button("Exportar Jugadores");
        exportPlayersButton.setPrefWidth(200);
        exportPlayersButton.setPrefHeight(40);
        exportPlayersButton.setStyle("-fx-font-size: 14px;");

        Button importPlayersButton = new Button("Importar Jugadores");
        importPlayersButton.setPrefWidth(200);
        importPlayersButton.setPrefHeight(40);
        importPlayersButton.setStyle("-fx-font-size: 14px;");

        Button exitButton = new Button("Salir");
        exitButton.setPrefWidth(200);
        exitButton.setPrefHeight(50);
        exitButton.setStyle("-fx-font-size: 16px;");
        
        // Add components to menu box
        menuBox.getChildren().addAll(
            titleLabel,
            new Separator(),
            createCardsButton,
            buildDeckButton,
            managePlayers,
            playGameButton,
            exportPlayersButton,
            importPlayersButton,
            exitButton
        );
        
        // Set menu in the center of the main container
        mainContainer.setCenter(menuBox);
        
        // Create scene
        Scene mainScene = new Scene(mainContainer, 600, 400);
        
        // Handle button actions
        createCardsButton.setOnAction(e -> {
            CardCreationView cardCreationView = new CardCreationView();
            Scene cardCreationScene = new Scene(cardCreationView, 600, 700);
            
            Stage cardCreationStage = new Stage();
            cardCreationStage.setTitle("Creador de Cartas");
            cardCreationStage.setScene(cardCreationScene);
            cardCreationStage.show();
            
            // Store reference to created cards
            cardCreationStage.setOnHiding(event -> {
                createdCards.clear();
                createdCards.addAll(cardCreationView.getCreatedCards());
            });
        });
        
        buildDeckButton.setOnAction(e -> {
            DeckBuilderView deckBuilderView = new DeckBuilderView(createdCards, playersManager);
            Scene deckBuilderScene = new Scene(deckBuilderView, 800, 600);
            
            Stage deckBuilderStage = new Stage();
            deckBuilderStage.setTitle("Constructor de Mazos");
            deckBuilderStage.setScene(deckBuilderScene);
            deckBuilderStage.show();
        });
        
        managePlayers.setOnAction(e -> {
            PlayersView playersView = new PlayersView(playersManager);
            Scene playersScene = new Scene(playersView, 750, 600);
            
            Stage playersStage = new Stage();
            playersStage.setTitle("Gestionar Jugadores");
            playersStage.setScene(playersScene);
            playersStage.show();
        });
        
        playGameButton.setOnAction(e -> {
            PlayGameView playGameView = new PlayGameView(playersManager);
            Scene playGameScene = new Scene(playGameView, 900, 700);
            Stage playGameStage = new Stage();
            playGameStage.setTitle("Jugar Partida");
            playGameStage.setScene(playGameScene);
            playGameStage.show();
        });
        
        exitButton.setOnAction(e -> {
            stage.close();
        });

        exportPlayersButton.setOnAction(e -> {
            try {
                playersManager.exportPlayers("players.json");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jugadores exportados correctamente.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al exportar jugadores: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        importPlayersButton.setOnAction(e -> {
            try {
                playersManager.importPlayers("players.json");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jugadores importados correctamente.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al importar jugadores: " + ex.getMessage());
                alert.showAndWait();
            }
        });
        
        // Set up stage
        stage.setTitle("Juego de Cartas");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
