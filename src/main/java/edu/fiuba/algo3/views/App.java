package edu.fiuba.algo3.views;

import edu.fiuba.algo3.controllers.AppController;
import edu.fiuba.algo3.views.components.NameInputView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        NameInputView nameInputView = new NameInputView();
        AppController controller = new AppController(stage);

        stage.setScene(nameInputView.createScene(controller::startGameWithNames));

        stage.setTitle("Ingresar Nombres De Jugadores");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}