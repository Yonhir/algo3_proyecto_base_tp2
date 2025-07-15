package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.scene.layout.VBox;

public class LeftColumn extends VBox {

    private static final double INFO_BOX_HEIGHT_RATIO = 0.2;
    private static final double SPECIAL_ZONE_HEIGHT_RATIO = 0.14;
    private static final double SPACER_HEIGHT_RATIO = 0.08;


    
    private final UISpecialZone UISpecialZoneList;
    private final UIPlayerInfo UICurrentPlayer;
    private final UIPlayerInfo UIOpponentPlayer;
    
    public LeftColumn(UISpecialZone UISpecialZoneList, Player currentPlayer, Player opponentPlayer) {
        super();
        this.UISpecialZoneList = UISpecialZoneList;
        this.UICurrentPlayer = new UIPlayerInfo(currentPlayer, true);
        this.UIOpponentPlayer = new UIPlayerInfo(opponentPlayer, false);
        setupLeftColumn();
    }
    
    private void setupLeftColumn() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 3px;");
        setSpacing(90);

        VBox specialZoneContainer = new VBox();
        specialZoneContainer.setAlignment(javafx.geometry.Pos.CENTER);
        specialZoneContainer.setStyle("-fx-background-color: #E6BE8A; -fx-border-color: #A0522D; -fx-border-width: 2px;");
        specialZoneContainer.getChildren().add(UISpecialZoneList);

        javafx.scene.layout.Region topSpacer = new javafx.scene.layout.Region();
        javafx.scene.layout.Region bottomSpacer = new javafx.scene.layout.Region();

        UICurrentPlayer.prefHeightProperty().bind(heightProperty().multiply(INFO_BOX_HEIGHT_RATIO));
        specialZoneContainer.prefHeightProperty().bind(heightProperty().multiply(SPECIAL_ZONE_HEIGHT_RATIO));
        UIOpponentPlayer.prefHeightProperty().bind(heightProperty().multiply(INFO_BOX_HEIGHT_RATIO));
        topSpacer.prefHeightProperty().bind(heightProperty().multiply(SPACER_HEIGHT_RATIO));
        bottomSpacer.prefHeightProperty().bind(heightProperty().multiply(SPACER_HEIGHT_RATIO));

        getChildren().addAll(topSpacer, UIOpponentPlayer, specialZoneContainer, UICurrentPlayer, bottomSpacer);


    }
}