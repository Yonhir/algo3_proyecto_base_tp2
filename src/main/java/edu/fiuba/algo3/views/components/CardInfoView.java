package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.ButtonCloseDescription;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UIUnit;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CardInfoView extends StackPane {

    private final BooleanProperty isCardInfoVisible = new SimpleBooleanProperty(false);

    public CardInfoView() {
        createCancelButton();
        // createInfoBox();
    }

    private void createCancelButton(){
        Button cancelButton = new Button("Cancel");

        cancelButton.visibleProperty().bind(isCardInfoVisible);
        cancelButton.managedProperty().bind(isCardInfoVisible);

        cancelButton.setStyle(
                "-fx-background-color: #d32f2f; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 8px 16px; " +
                        "-fx-border-radius: 4px; " +
                        "-fx-background-radius: 4px;"
        );

        cancelButton.toFront();

        getChildren().add(cancelButton);
        StackPane.setAlignment(cancelButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(cancelButton, new Insets(10));
        cancelButton.setOnAction(new ButtonCloseDescription(isCardInfoVisible, this));
    }
    
    public void showInfoCard(UICard card) {
        getChildren().removeIf(child -> !(child instanceof Button));
        
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

        StackPane.setMargin(infoBox, new Insets(15));
        
        getChildren().add(infoBox);

        isCardInfoVisible.set(true);
    }
}
