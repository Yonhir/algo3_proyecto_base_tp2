package edu.fiuba.algo3;

import edu.fiuba.algo3.views.App;
import edu.fiuba.algo3.models.sections.Board;
import javafx.application.Application;

public class Main {
    public static Board board;
    public static String nombreJugador1;
    public static String nombreJugador2;

    public static void main(String[] args) {
        try {
            board = new Board("Jugador 1", "Jugador 2");
        } catch (Exception e) {
            System.err.println("No se pudo inicializar el juego: " + e.getMessage());
            return;
        }

        Application.launch(App.class, args);
    }
}

