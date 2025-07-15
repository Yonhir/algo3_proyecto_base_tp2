package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.ButtonCloseDescription;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UIUnit;
import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CardInfoView extends StackPane {

    private final BooleanProperty showInfo = new SimpleBooleanProperty(false);

    public CardInfoView() {
        buttonClose();
    }

    private void buttonClose(){
        Button btnClose = new Button("x");

        btnClose.visibleProperty().bind(showInfo);
        btnClose.managedProperty().bind(showInfo);

        btnClose.setStyle(
                "-fx-background-color: black; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold;"
        );

        btnClose.toFront();

        getChildren().add(btnClose);
        StackPane.setAlignment(btnClose, Pos.TOP_RIGHT);
        StackPane.setMargin(btnClose, new Insets(10));
        btnClose.setOnAction(new ButtonCloseDescription(showInfo, this));
    }

    private void alert(){
        Label toast = new Label("Cierra la imagen anterior antes de abrir otra");
        toast.setTextFill(Color.WHITE);
        toast.setStyle(
                "-fx-background-color: rgba(0,0,0,0.7);" +
                        "-fx-padding: 10px 20px;" +
                        "-fx-background-radius: 5;"
        );

        StackPane toastContainer = new StackPane(toast);
        toastContainer.setPadding(new Insets(20, 0, 0, 0));
        StackPane.setAlignment(toastContainer, Pos.TOP_CENTER);

        toastContainer.setMouseTransparent(true);

        StackPane root = (StackPane) getScene().getRoot();
        root.getChildren().add(toastContainer);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), toastContainer);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), toastContainer);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2));

        fadeOut.setOnFinished(evt -> root.getChildren().remove(toastContainer));

        fadeIn.play();
        fadeIn.setOnFinished(e -> fadeOut.play());
    }

    public void showInfoCard(UICard card) {
        if (showInfo.get()) {
            alert();
            return;
        }
        VBox infoBox = new VBox(10);

        ImageView imageView = new ImageView(card.getBackgroundImage());

        imageView.setFitWidth(240);
        imageView.setFitHeight(360);

        infoBox.getChildren().add(imageView);

        Label nameLabel = new Label(card.getCardName());

        infoBox.getChildren().add(nameLabel);

        if (card.getDescription() != null){
            Label descriptionLabel = new Label(card.getDescription());
            infoBox.getChildren().add(descriptionLabel);
        }

        if (card instanceof UIUnit){
            Label modifiersLabel = new Label(((UIUnit) card).getModifiers());
            infoBox.getChildren().add(modifiersLabel);
        }

        infoBox.setAlignment(Pos.TOP_CENTER);

        infoBox.mouseTransparentProperty().setValue(true);

        getChildren().add(infoBox);

        StackPane.setMargin(infoBox, new Insets(15));

        showInfo.set(true);
    }
}
