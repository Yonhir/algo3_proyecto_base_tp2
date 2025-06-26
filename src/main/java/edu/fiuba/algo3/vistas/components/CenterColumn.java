package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.VBox;

public class CenterColumn extends VBox {
    
    private final Row opponentCloseCombat, opponentRanged, opponentSiege;
    private final Row playerCloseCombat, playerRanged, playerSiege;
    private final Hand handList;
    
    public CenterColumn(Row opponentCloseCombat, Row opponentRanged, Row opponentSiege,
                       Row playerCloseCombat, Row playerRanged, Row playerSiege,
                       Hand handList) {
        super();
        this.opponentCloseCombat = opponentCloseCombat;
        this.opponentRanged = opponentRanged;
        this.opponentSiege = opponentSiege;
        this.playerCloseCombat = playerCloseCombat;
        this.playerRanged = playerRanged;
        this.playerSiege = playerSiege;
        this.handList = handList;
        setupCenterColumn();
    }
    
    private void setupCenterColumn() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: #F7DC6F; -fx-border-color: #FFA500; -fx-border-width: 3px;"); // Orange background
        
        // Create the spacing region between rows
        javafx.scene.layout.Region opponentRowSpacer1 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region opponentRowSpacer2 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region playerRowSpacer1 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region playerRowSpacer2 = new javafx.scene.layout.Region();
        
        // Create opponent rows container
        VBox opponentRowsContainer = new VBox(); // Reduced spacing
        opponentRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        opponentRowsContainer.setStyle("-fx-background-color: #45B7D1; -fx-border-color: #0080FF; -fx-border-width: 2px;"); // Blue background
        opponentRowSpacer1.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.03));
        opponentRowSpacer2.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.03));
        
        // Bind individual row heights to container height
        opponentSiege.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.31));
        opponentRanged.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.31));
        opponentCloseCombat.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.31));
        
        opponentRowsContainer.getChildren().addAll(
                opponentSiege,  // Siege row (top)
                opponentRowSpacer1,
                opponentRanged,  // Ranged row (middle)
                opponentRowSpacer2,
                opponentCloseCombat   // Close Combat row (bottom)
        );

        // Create player rows container
        VBox playerRowsContainer = new VBox(); // Reduced spacing
        playerRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        playerRowsContainer.setStyle("-fx-background-color: #96CEB4; -fx-border-color: #00FF00; -fx-border-width: 2px;"); // Green background
        playerRowSpacer1.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.03));
        playerRowSpacer2.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.03));
        
        // Bind individual row heights to container height
        playerCloseCombat.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.31));
        playerRanged.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.31));
        playerSiege.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.31));
        
        playerRowsContainer.getChildren().addAll(
                playerCloseCombat,   // Close Combat row (top)
                playerRowSpacer1,
                playerRanged,   // Ranged row (middle)
                playerRowSpacer2,
                playerSiege    // Siege row (bottom)
        );
        
        // Create hand container
        VBox handContainer = new VBox(); // Reduced spacing
        handContainer.setAlignment(javafx.geometry.Pos.CENTER);
        handContainer.setStyle("-fx-background-color: #FFEAA7; -fx-border-color: #FFFF00; -fx-border-width: 2px;"); // Yellow background
        handContainer.getChildren().add(handList);
        
        // Bind container heights to center column height
        opponentRowsContainer.prefHeightProperty().bind(heightProperty().multiply(0.4));
        playerRowsContainer.prefHeightProperty().bind(heightProperty().multiply(0.4));
        handContainer.prefHeightProperty().bind(heightProperty().multiply(0.13));
        
        // Create spacing regions
        javafx.scene.layout.Region rowSpacer = new javafx.scene.layout.Region();
        rowSpacer.prefHeightProperty().bind(heightProperty().multiply(0.02)); // Small spacer between opponent and player rows
        javafx.scene.layout.Region handSpacer = new javafx.scene.layout.Region();
        handSpacer.prefHeightProperty().bind(heightProperty().multiply(0.05)); // Larger spacer between player rows and hand
        
        getChildren().addAll(
            opponentRowsContainer,
            rowSpacer,  // Small spacer between opponent and player rows
            playerRowsContainer,
            handSpacer,  // Larger spacer between player rows and hand
            handContainer
        );
    }
} 