package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class DeckBuilderView extends BorderPane {
    private ListView<String> availableCardsListView;
    private ListView<String> deckCardsListView;
    private ObservableList<String> availableCardsItems;
    private ObservableList<String> deckCardsItems;
    private List<Card> availableCards;
    private List<Card> deckCards;
    
    // Player selection
    private ComboBox<String> playerSelector;
    private PlayersManager playersManager;
    
    // Validation status labels
    private Label unitCountLabel;
    private Label specialCountLabel;
    private Label validationStatusLabel;
    
    // Constants from validators
    private static final int MIN_UNITS = 15;
    private static final int MIN_SPECIALS = 6;

    public DeckBuilderView(List<Unit> createdUnits) {
        this(createdUnits, null);
    }
    
    public DeckBuilderView(List<Unit> createdUnits, PlayersManager playersManager) {
        setPadding(new Insets(20));
        
        // Store players manager
        this.playersManager = playersManager;
        
        // Initialize card lists
        availableCards = new ArrayList<>();
        deckCards = new ArrayList<>();
        
        // Add created units to available cards
        availableCards.addAll(createdUnits);
        
        // Add special cards (4 of each concrete type that extends Special)
        addSpecialCards();
        
        // Create observable lists for UI
        availableCardsItems = FXCollections.observableArrayList();
        deckCardsItems = FXCollections.observableArrayList();
        
        // Populate available cards list
        updateAvailableCardsList();
        
        // Create UI components
        VBox topPanel = createTopPanel();
        VBox leftPanel = createLeftPanel();
        VBox rightPanel = createRightPanel();
        VBox centerPanel = createCenterPanel();
        
        // Add panels to the layout
        setTop(topPanel);
        setLeft(leftPanel);
        setCenter(centerPanel);
        setRight(rightPanel);
        
        // Update validation status
        updateValidationStatus();
    }
    
    private void addSpecialCards() {
        // Add BitingFrost cards
        for (int i = 0; i < 4; i++) {
            availableCards.add(new BitingFrost("Escarcha Mordiente", "Establece la fuerza de todas las cartas de Combate Cercano a 1 para ambos jugadores."));
        }
        
        // Add TorrentialRain cards - note: this actually affects Siege cards according to its implementation
        for (int i = 0; i < 4; i++) {
            availableCards.add(new TorrentialRain("Lluvia Torrencial", "Establece la fuerza de todas las cartas de Asedio a 1 para ambos jugadores."));
        }
        
        // Add ImpenetrableFog cards
        for (int i = 0; i < 4; i++) {
            availableCards.add(new ImpenetrableFog("Niebla Impenetrable", "Establece la fuerza de todas las cartas a Distancia a 1 para ambos jugadores."));
        }
        
        // Add ClearWeather cards
        for (int i = 0; i < 4; i++) {
            availableCards.add(new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima del campo."));
        }
    }
    
    private VBox createTopPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(0, 0, 10, 0));
        panel.setAlignment(Pos.CENTER_LEFT);
        
        // Create player selector if players manager is available
        if (playersManager != null && playersManager.getPlayerCount() > 0) {
            HBox selectorBox = new HBox(10);
            selectorBox.setAlignment(Pos.CENTER_LEFT);
            
            Label playerLabel = new Label("Jugador:");
            playerSelector = new ComboBox<>();
            
            // Populate player selector
            for (int i = 0; i < playersManager.getPlayerCount(); i++) {
                PlayerProfileManager player = playersManager.getPlayer(i);
                playerSelector.getItems().add(player.getPlayerName());
            }
            
            // Select active player by default
            if (playersManager.getActivePlayerIndex() >= 0) {
                playerSelector.getSelectionModel().select(playersManager.getActivePlayerIndex());
            } else if (playersManager.getPlayerCount() > 0) {
                playerSelector.getSelectionModel().select(0);
            }
            
            selectorBox.getChildren().addAll(playerLabel, playerSelector);
            panel.getChildren().add(selectorBox);
        }
        
        return panel;
    }
    
    private VBox createLeftPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("Cartas Disponibles");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        availableCardsListView = new ListView<>(availableCardsItems);
        availableCardsListView.setPrefWidth(300);
        availableCardsListView.setPrefHeight(500);
        
        panel.getChildren().addAll(titleLabel, availableCardsListView);
        return panel;
    }
    
    private VBox createRightPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("Mazo");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        deckCardsListView = new ListView<>(deckCardsItems);
        deckCardsListView.setPrefWidth(300);
        deckCardsListView.setPrefHeight(500);
        
        // Add deck stats and validation status
        VBox statsBox = new VBox(5);
        statsBox.setAlignment(Pos.CENTER_LEFT);
        statsBox.setPadding(new Insets(10, 0, 10, 0));
        
        unitCountLabel = new Label("Unidades: 0/" + MIN_UNITS);
        specialCountLabel = new Label("Especiales: 0/" + MIN_SPECIALS);
        validationStatusLabel = new Label("Estado: Mazo incompleto");
        validationStatusLabel.setTextFill(Color.RED);
        
        // Add separator
        Separator separator = new Separator();
        separator.setPadding(new Insets(5, 0, 5, 0));
        
        statsBox.getChildren().addAll(
            unitCountLabel,
            specialCountLabel,
            separator,
            validationStatusLabel
        );
        
        // Export button
        Button exportButton = new Button("Exportar Mazo");
        exportButton.setOnAction(e -> exportDeck());
        
        // Create Deck button
        Button createDeckButton = new Button("Crear Mazo");
        createDeckButton.setOnAction(e -> createDeck());
        
        // Buttons layout
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(createDeckButton, exportButton);
        
        panel.getChildren().addAll(titleLabel, deckCardsListView, statsBox, buttonsBox);
        return panel;
    }
    
    private VBox createCenterPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(20, 10, 10, 10));
        panel.setAlignment(Pos.CENTER);
        
        Button addToDeckButton = new Button("Añadir al Mazo >");
        addToDeckButton.setPrefWidth(150);
        addToDeckButton.setOnAction(e -> moveSelectedCardToDeck());
        
        Button removeFromDeckButton = new Button("< Quitar del Mazo");
        removeFromDeckButton.setPrefWidth(150);
        removeFromDeckButton.setOnAction(e -> moveSelectedCardFromDeck());
        
        // Disable buttons when no selection
        addToDeckButton.disableProperty().bind(availableCardsListView.getSelectionModel().selectedItemProperty().isNull());
        removeFromDeckButton.disableProperty().bind(deckCardsListView.getSelectionModel().selectedItemProperty().isNull());
        
        panel.getChildren().addAll(addToDeckButton, removeFromDeckButton);
        return panel;
    }
    
    private void moveSelectedCardToDeck() {
        int selectedIndex = availableCardsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Get the selected card
            Card selectedCard = availableCards.get(selectedIndex);
            
            // Move card from available to deck
            deckCards.add(selectedCard);
            availableCards.remove(selectedIndex);
            
            // Update UI lists
            String cardText = availableCardsItems.get(selectedIndex);
            availableCardsItems.remove(selectedIndex);
            deckCardsItems.add(cardText);
            
            // Select the next item if available
            if (selectedIndex < availableCardsItems.size()) {
                availableCardsListView.getSelectionModel().select(selectedIndex);
            } else if (availableCardsItems.size() > 0) {
                availableCardsListView.getSelectionModel().select(availableCardsItems.size() - 1);
            }
            
            // Update validation status
            updateValidationStatus();
        }
    }
    
    private void moveSelectedCardFromDeck() {
        int selectedIndex = deckCardsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Get the selected card
            Card selectedCard = deckCards.get(selectedIndex);
            
            // Move card from deck to available
            availableCards.add(selectedCard);
            deckCards.remove(selectedIndex);
            
            // Update UI lists
            String cardText = deckCardsItems.get(selectedIndex);
            deckCardsItems.remove(selectedIndex);
            availableCardsItems.add(cardText);
            
            // Select the next item if available
            if (selectedIndex < deckCardsItems.size()) {
                deckCardsListView.getSelectionModel().select(selectedIndex);
            } else if (deckCardsItems.size() > 0) {
                deckCardsListView.getSelectionModel().select(deckCardsItems.size() - 1);
            }
            
            // Update validation status
            updateValidationStatus();
        }
    }
    
    private void updateAvailableCardsList() {
        availableCardsItems.clear();
        for (Card card : availableCards) {
            availableCardsItems.add(getCardDisplayText(card));
        }
    }
    
    private String getCardDisplayText(Card card) {
        if (card instanceof Unit) {
            Unit unit = (Unit) card;
            String unitType = "";
            if (unit.canBeInCloseCombat()) {
                unitType = "Combate Cercano";
            } else if (unit.canBeInRanged()) {
                unitType = "A Distancia";
            } else if (unit.canBeInSiege()) {
                unitType = "Asedio";
            }
            return unit.getName() + " - Puntos: " + unit.getBasePoints() + " - Tipo: " + unitType;
        } else if (card instanceof Special) {
            Special special = (Special) card;
            return special.getName() + " - Especial: " + special.getClass().getSimpleName();
        }
        return card.getName();
    }
    
    private void updateValidationStatus() {
        int unitCount = 0;
        int specialCount = 0;
        
        for (Card card : deckCards) {
            if (card instanceof Unit) {
                unitCount++;
            } else if (card instanceof Special) {
                specialCount++;
            }
        }
        
        unitCountLabel.setText("Unidades: " + unitCount + "/" + MIN_UNITS);
        specialCountLabel.setText("Especiales: " + specialCount + "/" + MIN_SPECIALS);
        
        boolean isValid = unitCount >= MIN_UNITS && specialCount >= MIN_SPECIALS;
        
        if (isValid) {
            validationStatusLabel.setText("Estado: Mazo válido");
            validationStatusLabel.setTextFill(Color.GREEN);
        } else {
            validationStatusLabel.setText("Estado: Mazo incompleto");
            validationStatusLabel.setTextFill(Color.RED);
        }
    }
    
    private void createDeck() {
        // Validate deck
        int unitCount = 0;
        int specialCount = 0;
        
        for (Card card : deckCards) {
            if (card instanceof Unit) {
                unitCount++;
            } else if (card instanceof Special) {
                specialCount++;
            }
        }
        
        if (unitCount < MIN_UNITS || specialCount < MIN_SPECIALS) {
            showAlert(Alert.AlertType.ERROR, "Mazo Inválido", 
                "El mazo debe tener al menos " + MIN_UNITS + " unidades y " + MIN_SPECIALS + " cartas especiales.");
            return;
        }
        
        // Create deck
        try {
            Deck deck = new Deck(new ArrayList<>(deckCards));
            
            // If we have a players manager and a selected player, add the deck to that player
            if (playersManager != null && playerSelector != null && playerSelector.getValue() != null) {
                int selectedIndex = playerSelector.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    PlayerProfileManager selectedPlayer = playersManager.getPlayer(selectedIndex);
                    if (selectedPlayer != null) {
                        selectedPlayer.addDeck(deck);
                        showAlert(Alert.AlertType.INFORMATION, "Mazo Creado", 
                            "El mazo ha sido creado y añadido al jugador " + selectedPlayer.getPlayerName());
                        return;
                    }
                }
            }
            
            // If no player was selected or available, just show success message
            showAlert(Alert.AlertType.INFORMATION, "Mazo Creado", 
                "El mazo ha sido creado exitosamente.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", 
                "Error al crear el mazo: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void exportDeck() {
        // Validate deck
        int unitCount = 0;
        int specialCount = 0;
        
        for (Card card : deckCards) {
            if (card instanceof Unit) {
                unitCount++;
            } else if (card instanceof Special) {
                specialCount++;
            }
        }
        
        if (unitCount < MIN_UNITS || specialCount < MIN_SPECIALS) {
            showAlert(Alert.AlertType.ERROR, "Mazo Inválido", 
                "El mazo debe tener al menos " + MIN_UNITS + " unidades y " + MIN_SPECIALS + " cartas especiales.");
            return;
        }
        
        // Create JSON object for the deck
        JSONObject deckJson = new JSONObject();
        JSONArray cardsArray = new JSONArray();
        
        for (Card card : deckCards) {
            JSONObject cardJson = new JSONObject();
            cardJson.put("name", card.getName());
            
            if (card instanceof Unit) {
                Unit unit = (Unit) card;
                cardJson.put("type", "Unit");
                String unitType = "";
                if (unit.canBeInCloseCombat()) {
                    unitType = "CloseCombat";
                } else if (unit.canBeInRanged()) {
                    unitType = "Ranged";
                } else if (unit.canBeInSiege()) {
                    unitType = "Siege";
                }
                cardJson.put("unitType", unitType);
                cardJson.put("points", unit.getBasePoints());
                
                // Add modifiers
                JSONArray modifiersArray = new JSONArray();
                if (unit.haveModifier(new Agil())) {
                    modifiersArray.add("Agil");
                }
                if (unit.haveModifier(new TightBond())) {
                    modifiersArray.add("TightBond");
                }
                cardJson.put("modifiers", modifiersArray);
            } else if (card instanceof Special) {
                cardJson.put("type", "Special");
                cardJson.put("specialType", card.getClass().getSimpleName());
            }
            
            cardsArray.add(cardJson);
        }
        
        deckJson.put("cards", cardsArray);
        
        // Save to file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Mazo");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos JSON", "*.json")
        );
        
        // Default file name based on player if available
        if (playersManager != null && playerSelector != null && playerSelector.getValue() != null) {
            String playerName = playerSelector.getValue();
            fileChooser.setInitialFileName(playerName + "_deck.json");
        } else {
            fileChooser.setInitialFileName("deck.json");
        }
        
        File file = fileChooser.showSaveDialog(getScene().getWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(deckJson.toJSONString());
                writer.flush();
                
                showAlert(Alert.AlertType.INFORMATION, "Exportación Exitosa", 
                    "El mazo ha sido exportado exitosamente a " + file.getName());
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Error de Exportación", 
                    "Error al exportar el mazo: " + e.getMessage());
            }
        }
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 