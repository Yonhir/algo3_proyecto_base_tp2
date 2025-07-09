package edu.fiuba.algo3.views;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.components.NameInputView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private String nombreJugador1;
    private String nombreJugador2;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        NameInputView nameInputView = new NameInputView();

        stage.setScene(nameInputView.createScene(stage, (nombre1, nombre2) -> {
            this.nombreJugador1 = nombre1;
            this.nombreJugador2 = nombre2;
            startGame();
        }));
        stage.setTitle("Ingresar Nombres De Jugadores");
        stage.show();

    }
    private void startGame() {
        Board board;
        try {
            board = new Board(nombreJugador1, nombreJugador2);
        } catch (Exception e) {
            System.err.println("No se pudo inicializar el juego: " + e.getMessage());
            return;
        }

        GameView gameView = new GameView(
                board.getCurrentPlayerHand(),
                board.getPlayer1Deck(), board.getPlayer2Deck(),
                board.getPlayer1DiscardPile(), board.getPlayer2DiscardPile(),
                board.getPlayer1CloseCombat(), board.getPlayer1Ranged(), board.getPlayer1Siege(),
                board.getPlayer2CloseCombat(), board.getPlayer2Ranged(), board.getPlayer2Siege(),
                board.getSpecialZone()
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

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}