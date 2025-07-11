package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.InputStream;

public class CardInfoView extends StackPane {

    private BooleanProperty showInfo = new SimpleBooleanProperty(false);;

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

        getChildren().add(btnClose);
        StackPane.setAlignment(btnClose, Pos.TOP_RIGHT);
        StackPane.setMargin(btnClose, new Insets(10));
        btnClose.setOnAction(this::buttonClose);
    }

    private void buttonClose(ActionEvent event) {
        if (showInfo.getValue()) {
            getChildren().remove(getChildren().size()-1);
            showInfo.set(false);
        }
    }

    private void alert(String message){
        Label toast = new Label(message);
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
            alert("Cierra la imagen anterior antes de abrir otra");
            return;
        }

        String cardName = card.getModel().getName();
        if (cardName == null || cardName.trim().isEmpty()) {
            return;
        }

        String pngPath = "/images/" + cardName + ".png";
        InputStream pngStream = getClass().getResourceAsStream(pngPath);

        if (pngStream != null) {
            Image image = new Image(pngStream);

            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);

            imageView.fitWidthProperty().bind(widthProperty());
            imageView.fitHeightProperty().bind(heightProperty());

            getChildren().add(imageView);

            showInfo.set(true);
        }
    }
}
