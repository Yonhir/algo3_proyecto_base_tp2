package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.AppController;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.components.NameInputView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private Board board;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        board = new Board();

        NameInputView nameInputView = new NameInputView();
        AppController controller = new AppController(stage, board);

        stage.setScene(nameInputView.createScene(stage, (nombre1, nombre2) -> {
            controller.startGameWithNames(nombre1, nombre2);
        }));

        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}