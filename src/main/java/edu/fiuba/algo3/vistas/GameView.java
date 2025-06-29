package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.components.*;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ClearWeather;
import edu.fiuba.algo3.modelo.cards.specials.Scorch;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import edu.fiuba.algo3.modelo.colors.Blue;
import edu.fiuba.algo3.modelo.colors.Red;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GameView extends StackPane {
    private final Hand handList;
    private final Row opponentCloseCombat, opponentRanged, opponentSiege;
    private final Row playerCloseCombat, playerRanged, playerSiege;
    private final SpecialZone specialZoneList;
    private final Deck playerDeck, opponentDeck;
    private final DiscardPile playerDiscardPile, opponentDiscardPile;
    private final TopBar topBar;
    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;
    
    private Scene scene;

    public GameView() {
        handList = new Hand();
        opponentCloseCombat = new Row();
        opponentRanged = new Row();
        opponentSiege = new Row();
        playerCloseCombat = new Row();
        playerRanged = new Row();
        playerSiege = new Row();
        specialZoneList = new SpecialZone();
        playerDeck = new Deck();
        opponentDeck = new Deck();
        playerDiscardPile = new DiscardPile();
        opponentDiscardPile = new DiscardPile();
        
        topBar = new TopBar();
        leftColumn = new LeftColumn(specialZoneList);
        centerColumn = new CenterColumn(opponentCloseCombat, opponentRanged, opponentSiege,
                                      playerCloseCombat, playerRanged, playerSiege, handList);
        rightColumn = new RightColumn(playerDeck, opponentDeck, playerDiscardPile, opponentDiscardPile);
    }

    private void setupSceneSizeListeners() {
        playerDeck.setupSceneSizeListener(scene);
        playerDiscardPile.setupSceneSizeListener(scene);
        opponentDeck.setupSceneSizeListener(scene);
        opponentDiscardPile.setupSceneSizeListener(scene);
    }

    public Scene createScene() {
        double windowWidth = 1920;
        double windowHeight = 1080;

        HBox gameBoardLayout = new HBox();
        gameBoardLayout.setAlignment(javafx.geometry.Pos.CENTER);
        gameBoardLayout.setStyle("-fx-background-color: #8B4513; -fx-border-color: #654321; -fx-border-width: 4px;");

        leftColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.2));
        centerColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.50));
        rightColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.3));
        gameBoardLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);

        VBox.setVgrow(leftColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(rightColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(gameBoardLayout, javafx.scene.layout.Priority.ALWAYS);

        VBox mainContent = new VBox();
        mainContent.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        mainContent.setStyle("-fx-background-color: #654321; -fx-border-color: #3E2723; -fx-border-width: 5px;"); // Brown background
        topBar.prefHeightProperty().bind(mainContent.heightProperty().multiply(0.05));
        gameBoardLayout.prefHeightProperty().bind(mainContent.heightProperty().multiply(0.95));
        mainContent.getChildren().addAll(topBar, gameBoardLayout);
        
        getChildren().add(mainContent);

        scene = new Scene(this, windowWidth, windowHeight);

        setupSceneSizeListeners();

        return scene;
    }

    public void showExitConfirmation() {
        GameMenuOverlay.hide(this);
        ExitConfirmationDialog.show(this);
    }

    public void setOnMenuRequested(EventHandler<ActionEvent> handler) {
        topBar.getMenuButton().setOnAction(handler);
    }

    public void loadSampleCardsForPreview() {
        // Create sample unit cards for different row types
        List<Unit> closeCombatUnits = createSampleCloseCombatUnits();
        List<Unit> rangedUnits = createSampleRangedUnits();
        List<Unit> siegeUnits = createSampleSiegeUnits();
        List<Special> specialCards = createSampleSpecialCards();
        
        // Add cards to opponent rows (top)
        for (Unit unit : closeCombatUnits.subList(0, Math.min(3, closeCombatUnits.size()))) {
            opponentCloseCombat.addCard(new UnitCard(unit));
        }
        for (Unit unit : rangedUnits.subList(0, Math.min(2, rangedUnits.size()))) {
            opponentRanged.addCard(new UnitCard(unit));
        }
        for (Unit unit : siegeUnits.subList(0, Math.min(2, siegeUnits.size()))) {
            opponentSiege.addCard(new UnitCard(unit));
        }
        
        // Add cards to player rows (bottom)
        for (Unit unit : closeCombatUnits.subList(3, Math.min(6, closeCombatUnits.size()))) {
            playerCloseCombat.addCard(new UnitCard(unit));
        }
        for (Unit unit : rangedUnits.subList(2, Math.min(4, rangedUnits.size()))) {
            playerRanged.addCard(new UnitCard(unit));
        }
        for (Unit unit : siegeUnits.subList(2, Math.min(5, siegeUnits.size()))) {
            playerSiege.addCard(new UnitCard(unit));
        }
        
        // Add special cards to special zone
        for (Special special : specialCards) {
            specialZoneList.addCard(new SpecialCard(special));
        }
        
        // Add some cards to hand for preview
        List<Unit> handUnits = new ArrayList<>();
        handUnits.addAll(closeCombatUnits.subList(6, Math.min(8, closeCombatUnits.size())));
        handUnits.addAll(rangedUnits.subList(4, Math.min(6, rangedUnits.size())));
        handUnits.addAll(siegeUnits.subList(5, Math.min(7, siegeUnits.size())));
        
        for (Unit unit : handUnits) {
            handList.addCard(new UnitCard(unit));
        }
    }
    
    private List<Unit> createSampleCloseCombatUnits() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit("Geralt de Rivia", "El Brujo Blanco", 15, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Ciri", "La Princesa de Cintra", 12, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Guerrero del Clan an Craite", "Feroz guerrero del norte", 8, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Comando de Rayas Azules", "Soldados de élite", 6, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Fuerza de Defensa Bovina", "Defensores leales", 4, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Vampiro Katakan", "Criatura de la noche", 10, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Cahir Mawr Dyffryn", "Caballero de Nilfgaard", 7, new CloseCombatType(), new ArrayList<>()));
        units.add(new Unit("Cerys", "Reina de Skellige", 9, new CloseCombatType(), new ArrayList<>()));
        
        // Set colors for opponent and player cards
        for (int i = 0; i < 4; i++) {
            units.get(i).setColor(new Red()); // Opponent cards
        }
        for (int i = 4; i < units.size(); i++) {
            units.get(i).setColor(new Blue()); // Player cards
        }
        
        return units;
    }
    
    private List<Unit> createSampleRangedUnits() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit("Arquero de Infantería Negro", "Arquero experto", 10, new RangedType(), new ArrayList<>()));
        units.add(new Unit("Cazadores de Dragones de Crinfrid", "Cazadores especializados", 7, new RangedType(), new ArrayList<>()));
        units.add(new Unit("Ballesta", "Arma de precisión", 6, new RangedType(), new ArrayList<>()));
        units.add(new Unit("Blueboy Lugos", "Hijo del rey", 8, new RangedType(), new ArrayList<>()));
        units.add(new Unit("Ciaran aep Easnillien", "Elfo arquero", 9, new RangedType(), new ArrayList<>()));
        units.add(new Unit("Thaler", "Espía de Temeria", 5, new RangedType(), new ArrayList<>()));
        
        // Set colors for opponent and player cards
        for (int i = 0; i < 2; i++) {
            units.get(i).setColor(new Red()); // Opponent cards
        }
        for (int i = 2; i < units.size(); i++) {
            units.get(i).setColor(new Blue()); // Player cards
        }
        
        return units;
    }
    
    private List<Unit> createSampleSiegeUnits() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit("Catapulta", "Arma de asedio", 8, new SiegeType(), new ArrayList<>()));
        units.add(new Unit("Torre de Asedio", "Defensa fortificada", 6, new SiegeType(), new ArrayList<>()));
        units.add(new Unit("Barclay Els", "Enano herrero", 7, new SiegeType(), new ArrayList<>()));
        units.add(new Unit("Birna Bran", "Reina de Skellige", 9, new SiegeType(), new ArrayList<>()));
        units.add(new Unit("Botchling", "Criatura deforme", 4, new SiegeType(), new ArrayList<>()));
        units.add(new Unit("Celaeno Harpía", "Monstruo volador", 11, new SiegeType(), new ArrayList<>()));
        units.add(new Unit("Cuerno del comandante", "Artefacto mágico", 5, new SiegeType(), new ArrayList<>()));
        
        // Set colors for opponent and player cards
        for (int i = 0; i < 2; i++) {
            units.get(i).setColor(new Red()); // Opponent cards
        }
        for (int i = 2; i < units.size(); i++) {
            units.get(i).setColor(new Blue()); // Player cards
        }
        
        return units;
    }
    
    private List<Special> createSampleSpecialCards() {
        List<Special> specials = new ArrayList<>();
        specials.add(new BitingFrost("Escarcha mordaz", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"));
        specials.add(new ImpenetrableFog("Niebla impenetrable", "Reduce todas las unidades a distancia a 1 punto"));
        specials.add(new TorrentialRain("Lluvia torrencial", "Reduce todas las unidades de asedio a 1 punto"));
        specials.add(new ClearWeather("Tiempo despejado", "Elimina todos los efectos de clima"));
        specials.add(new Scorch("Quemar", "Elimina la unidad más fuerte del campo", List.of(new edu.fiuba.algo3.modelo.sections.types.SpecialType())));
        
        // Set colors for special cards (they can be used by both players)
        for (Special special : specials) {
            special.setColor(new Blue());
        }
        
        return specials;
    }
}
