package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.AppController;
import edu.fiuba.algo3.models.sections.Board;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Board board = new Board();

        AppController controller = new AppController(stage, board);

        controller.loadNameInputView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
