package edu.fiuba.algo3.views;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.components.NameInputView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static edu.fiuba.algo3.Main.board;

public class App extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) {
            this.stage = stage;

            if (Main.nombreJugador1 == null || Main.nombreJugador2 == null) {
                NameInputView nameInputView = new NameInputView();
                stage.setScene(nameInputView.createScene(stage));
                stage.setTitle("Ingresar Nombres De Jugadores");
                stage.show();
                return;
            }

            try {
                Main.board = new Board(Main.nombreJugador1, Main.nombreJugador2);
            } catch (Exception e) {
                System.err.println("No se pudo inicializar el juego: " + e.getMessage());
                return;
            }


        GameView gameView = new GameView(
                board.currentPlayerHand(),
                board.player1Deck(), board.player2Deck(),
                board.player1DiscardPile(), board.player2DiscardPile(),
                board.player1CloseCombat(), board.player1Ranged(), board.player1Siege(),
                board.player2CloseCombat(), board.player2Ranged(), board.player2Siege(),
                board.specialZone()
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