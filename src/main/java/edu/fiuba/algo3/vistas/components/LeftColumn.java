package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.VBox;

public class LeftColumn extends VBox {
    
    private final SpecialZone specialZoneList;
    
    public LeftColumn(SpecialZone specialZoneList) {
        super();
        this.specialZoneList = specialZoneList;
        setupLeftColumn();
    }
    
    private void setupLeftColumn() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 3px;"); // Brown background
        
        // Create a special zone container with dynamic width
        VBox specialZoneContainer = new VBox(); // Reduced spacing
        specialZoneContainer.setAlignment(javafx.geometry.Pos.CENTER);
        specialZoneContainer.setStyle("-fx-background-color: #E6BE8A; -fx-border-color: #A0522D; -fx-border-width: 2px;"); // Brown background
        specialZoneContainer.getChildren().add(specialZoneList);
        
        // Create spacing regions for left column
        javafx.scene.layout.Region topSpacer = new javafx.scene.layout.Region();
        javafx.scene.layout.Region bottomSpacer = new javafx.scene.layout.Region();

        // Bind the spacing regions to the left column height
        topSpacer.prefHeightProperty().bind(heightProperty().multiply(0.43)); // 43% of column height
        specialZoneContainer.prefHeightProperty().bind(heightProperty().multiply(0.14)); // 14% of column height
        bottomSpacer.prefHeightProperty().bind(heightProperty().multiply(0.43)); // 43% of column height
        
        getChildren().addAll(topSpacer, specialZoneContainer, bottomSpacer);
    }
} 