package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        ViewManager viewManager = new ViewManager(stage);
        viewManager.showMainMenu();
        
        // Handle window close request (X button)
        stage.setOnCloseRequest(event -> {
            event.consume(); // Prevent default close behavior
            viewManager.showExitConfirmation();
        });
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}