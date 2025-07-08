package edu.fiuba.algo3.views;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.json.GameLoader;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.turnManagement.Game;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.views.components.ExitConfirmationDialog;
import edu.fiuba.algo3.views.components.NameInputView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) {
            this.stage = stage;

            if (Main.nombreJugador1 == null || Main.nombreJugador2 == null) {
                // aún no ingresaron los nombres → mostrar NameInputView
                NameInputView nameInputView = new NameInputView();
                stage.setScene(nameInputView.createScene(stage));
                stage.setTitle("Ingresar Nombres De Jugadores");
                stage.show();
                return;
            }

            try {
                // ya están los nombres → crear modelo
                GameInitializer initializer = new GameInitializer();
                Main.initialState = initializer.createInitialState(Main.nombreJugador1, Main.nombreJugador2);
            } catch (Exception e) {
                System.err.println("No se pudo inicializar el juego: " + e.getMessage());
                return;
            }

            GameState estado = Main.initialState;

            GameView gameView = new GameView(
                    estado.currentPlayerHand,
                    estado.player1Deck, estado.player2Deck,
                    estado.player1DiscardPile, estado.player2DiscardPile,
                    estado.player1CloseCombat, estado.player1Ranged, estado.player1Siege,
                    estado.player2CloseCombat, estado.player2Ranged, estado.player2Siege,
                    estado.specialZone
            );

            Scene scene = gameView.createScene();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Gwent - Juego en curso");

            stage.setOnCloseRequest(event -> {
                event.consume();
                gameView.showExitConfirmation();
            });

            stage.show();
        }

    }