package edu.fiuba.algo3;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.turnManagement.Game;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.json.GameLoader;
import edu.fiuba.algo3.views.App;
import edu.fiuba.algo3.views.GameInitializer;
import edu.fiuba.algo3.views.GameState;
import javafx.application.Application;

public class Main {
    public static GameState initialState;
    public static String nombreJugador1;
    public static String nombreJugador2;

    public static void main(String[] args) {
        try {
            initialState = new GameInitializer().createInitialState("Jugador 1", "Jugador 2");
        } catch (Exception e) {
            System.err.println("No se pudo inicializar el juego: " + e.getMessage());
            return;
        }

        Application.launch(App.class, args);
    }
}

