package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UIUnit;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class CardInfoView extends StackPane {

    private final BooleanProperty isCardInfoVisible = new SimpleBooleanProperty(false);
    private List<UIRow> rows;
    private UISpecialZone specialZone;

    public CardInfoView() {
    }

    public void setRows(List<UIRow> rows) {
        this.rows = rows;
    }
    
    public void setSpecialZone(UISpecialZone specialZone) {
        this.specialZone = specialZone;
    }
    
    public void showInfoCard(UICard card) {
        getChildren().clear();
        
        VBox infoBox = new VBox(15);
        infoBox.setAlignment(Pos.TOP_CENTER);
        infoBox.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.9); " +
                "-fx-background-radius: 15px; " +
                "-fx-border-color: #d4af37; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 15px; " +
                "-fx-padding: 20px;"
        );

        // Bigger copy of the UICard
        UICard bigCard = card.copy();
        bigCard.scaleCard(2.0, 2.0); // Make it 100% bigger (double the size)
        bigCard.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 5); " +
                "-fx-background-radius: 10px;"
        );

        infoBox.getChildren().add(bigCard);

        // Card Name with elegant styling
        Label nameLabel = new Label(card.getCardName());
        nameLabel.setStyle(
                "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 2, 0, 0, 1);"
        );
        nameLabel.setWrapText(true);
        nameLabel.setMaxWidth(260);

        infoBox.getChildren().add(nameLabel);

        // Description with improved styling
        if (card.getDescription() != null){
            Label descriptionLabel = new Label(card.getDescription());
            descriptionLabel.setStyle(
                    "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
                    "-fx-font-size: 14px; " +
                    "-fx-text-fill: #e0e0e0; " +
                    "-fx-text-alignment: center; " +
                    "-fx-line-spacing: 2px;"
            );
            descriptionLabel.setWrapText(true);
            descriptionLabel.setMaxWidth(260);
            descriptionLabel.setAlignment(Pos.CENTER);
            
            infoBox.getChildren().add(descriptionLabel);
        }

        // Modifiers with special styling
        if (card instanceof UIUnit){
            String modifiersText = ((UIUnit) card).getModifiers();
            if (!modifiersText.isEmpty()) {
                Label modifiersLabel = new Label("Modificadores:");
                modifiersLabel.setStyle(
                        "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #d4af37; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 1, 0, 0, 1);"
                );
                infoBox.getChildren().add(modifiersLabel);
                
                Label modifiersContentLabel = new Label(modifiersText);
                modifiersContentLabel.setStyle(
                        "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
                        "-fx-font-size: 12px; " +
                        "-fx-text-fill: #b8b8b8; " +
                        "-fx-text-alignment: center; " +
                        "-fx-line-spacing: 3px; " +
                        "-fx-background-color: rgba(212, 175, 55, 0.1); " +
                        "-fx-background-radius: 8px; " +
                        "-fx-padding: 8px;"
                );
                modifiersContentLabel.setWrapText(true);
                modifiersContentLabel.setMaxWidth(260);
                modifiersContentLabel.setAlignment(Pos.CENTER);
                
                infoBox.getChildren().add(modifiersContentLabel);
            }
        }

        infoBox.mouseTransparentProperty().setValue(true);

        StackPane.setMargin(infoBox, new Insets(20, 20, 60, 20));
        
        getChildren().add(infoBox);

        isCardInfoVisible.set(true);
        
        // Switch on rows and special zone when showing card info
        if (rows != null) {
            switchOnRows(card);
        }
        if (specialZone != null) {
            switchOnSpecialZone(card);
        }
    }
    
    private void switchOnRows(UICard card) {
        // Switch off rows first if there was a previous selection
        switchOffRows();
        
        for (UIRow row : rows) {
            row.switchOn(card.getModelCard());
        }
    }
    
    private void switchOnSpecialZone(UICard card) {
        // Switch off special zone first if there was a previous selection
        switchOffSpecialZone();
        
        specialZone.switchOn(card.getModelCard());
    }

    private void switchOffRows() {
        if (rows != null) {
            for (UIRow row : rows) {
                row.switchOff();
            }
        }
    }
    
    private void switchOffSpecialZone() {
        if (specialZone != null) {
            specialZone.switchOff();
        }
    }
}
