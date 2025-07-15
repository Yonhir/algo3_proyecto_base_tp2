package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.PlayerNameScreen;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PlayerNameScreenController implements EventHandler<ActionEvent> {
    
    private final PlayerNameScreen playerNameScreen;

    public PlayerNameScreenController(PlayerNameScreen playerNameScreen){
        this.playerNameScreen = playerNameScreen;
    }

    @Override
    public void handle(ActionEvent event) {
        playerNameScreen.hide();
    }
}
