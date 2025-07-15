package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.views.components.cardlist.CardList;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.scene.layout.VBox;

public class LeftColumn extends VBox {
    // Layout constants
    private static final double TOP_SPACER_HEIGHT_RATIO = 0.43;
    private static final double SPECIAL_ZONE_HEIGHT_RATIO = 0.14;
    private static final double BOTTOM_SPACER_HEIGHT_RATIO = 0.43;
    

    
    private final UISpecialZone UISpecialZoneList;
    
    public LeftColumn(UISpecialZone UISpecialZoneList) {
        super();
        this.UISpecialZoneList = UISpecialZoneList;
        setupLeftColumn();
    }
    
    private void setupLeftColumn() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 3px;");
        
        VBox specialZoneContainer = new VBox();
        specialZoneContainer.setAlignment(javafx.geometry.Pos.CENTER);
        specialZoneContainer.setStyle("-fx-background-color: #E6BE8A; -fx-border-color: #A0522D; -fx-border-width: 2px;");
        specialZoneContainer.getChildren().add(UISpecialZoneList);
        
        javafx.scene.layout.Region topSpacer = new javafx.scene.layout.Region();
        javafx.scene.layout.Region bottomSpacer = new javafx.scene.layout.Region();

        topSpacer.prefHeightProperty().bind(heightProperty().multiply(TOP_SPACER_HEIGHT_RATIO));
        specialZoneContainer.prefHeightProperty().bind(heightProperty().multiply(SPECIAL_ZONE_HEIGHT_RATIO));
        bottomSpacer.prefHeightProperty().bind(heightProperty().multiply(BOTTOM_SPACER_HEIGHT_RATIO));
        
        getChildren().addAll(topSpacer, specialZoneContainer, bottomSpacer);
    }

    public UISpecialZone getUISpecialZone() { return UISpecialZoneList; }
}