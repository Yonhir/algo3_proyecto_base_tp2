package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.CardList;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import javafx.scene.layout.VBox;

import java.util.List;

public class CenterColumn extends VBox {
    // Layout constants
    private static final double ROW_SPACER_RATIO = 0.03;
    private static final double ROW_HEIGHT_RATIO = 0.31;
    private static final double CONTAINER_HEIGHT_RATIO = 0.4;
    private static final double HAND_CONTAINER_HEIGHT_RATIO = 0.13;
    private static final double ROW_SPACER_HEIGHT_RATIO = 0.02;
    private static final double HAND_SPACER_HEIGHT_RATIO = 0.05;
    

    
    private final UIRow opponentCloseCombat, opponentRanged, opponentSiege;
    private final UIRow playerCloseCombat, playerRanged, playerSiege;
    private final UIHand UIHandList;
    
    public CenterColumn(UIRow opponentCloseCombat, UIRow opponentRanged, UIRow opponentSiege,
                        UIRow playerCloseCombat, UIRow playerRanged, UIRow playerSiege,
                        UIHand UIHandList) {
        super();
        this.opponentCloseCombat = opponentCloseCombat;
        this.opponentRanged = opponentRanged;
        this.opponentSiege = opponentSiege;
        this.playerCloseCombat = playerCloseCombat;
        this.playerRanged = playerRanged;
        this.playerSiege = playerSiege;
        this.UIHandList = UIHandList;
        setupCenterColumn();
    }
    
    private void setupCenterColumn() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: #D2691E; -fx-border-color: #A0522D; -fx-border-width: 3px;");
        
        javafx.scene.layout.Region opponentRowSpacer1 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region opponentRowSpacer2 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region playerRowSpacer1 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region playerRowSpacer2 = new javafx.scene.layout.Region();
        
        VBox opponentRowsContainer = new VBox();
        opponentRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        opponentRowsContainer.setStyle("-fx-background-color: #CD853F; -fx-border-color: #8B4513; -fx-border-width: 2px;");
        opponentRowSpacer1.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(ROW_SPACER_RATIO));
        opponentRowSpacer2.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(ROW_SPACER_RATIO));
        
        opponentSiege.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(ROW_HEIGHT_RATIO));
        opponentRanged.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(ROW_HEIGHT_RATIO));
        opponentCloseCombat.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(ROW_HEIGHT_RATIO));
        
        opponentRowsContainer.getChildren().addAll(
                opponentSiege,
                opponentRowSpacer1,
                opponentRanged,
                opponentRowSpacer2,
                opponentCloseCombat
        );

        VBox playerRowsContainer = new VBox();
        playerRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        playerRowsContainer.setStyle("-fx-background-color: #DEB887; -fx-border-color: #A0522D; -fx-border-width: 2px;");
        playerRowSpacer1.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(ROW_SPACER_RATIO));
        playerRowSpacer2.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(ROW_SPACER_RATIO));
        
        playerCloseCombat.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(ROW_HEIGHT_RATIO));
        playerRanged.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(ROW_HEIGHT_RATIO));
        playerSiege.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(ROW_HEIGHT_RATIO));
        
        playerRowsContainer.getChildren().addAll(
                playerCloseCombat,
                playerRowSpacer1,
                playerRanged,
                playerRowSpacer2,
                playerSiege
        );
        
        VBox handContainer = new VBox();
        handContainer.setAlignment(javafx.geometry.Pos.CENTER);
        handContainer.setStyle("-fx-background-color: #F4A460; -fx-border-color: #D2691E; -fx-border-width: 2px;");
        handContainer.getChildren().add(UIHandList);
        
        opponentRowsContainer.prefHeightProperty().bind(heightProperty().multiply(CONTAINER_HEIGHT_RATIO));
        playerRowsContainer.prefHeightProperty().bind(heightProperty().multiply(CONTAINER_HEIGHT_RATIO));
        handContainer.prefHeightProperty().bind(heightProperty().multiply(HAND_CONTAINER_HEIGHT_RATIO));
        
        javafx.scene.layout.Region rowSpacer = new javafx.scene.layout.Region();
        rowSpacer.prefHeightProperty().bind(heightProperty().multiply(ROW_SPACER_HEIGHT_RATIO));
        javafx.scene.layout.Region handSpacer = new javafx.scene.layout.Region();
        handSpacer.prefHeightProperty().bind(heightProperty().multiply(HAND_SPACER_HEIGHT_RATIO));
        
        getChildren().addAll(
            opponentRowsContainer,
            rowSpacer,
            playerRowsContainer,
            handSpacer,
            handContainer
        );
    }

    public List<UIRow> getUIRows() { return List.of(opponentCloseCombat,opponentRanged, opponentSiege, playerCloseCombat, playerRanged, playerSiege); }

    public List<UICard> getUICards() { return UIHandList.getCards(); }

    public UIHand getUIHand() { return UIHandList; }
}