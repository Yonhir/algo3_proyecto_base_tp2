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
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PlayerProfileView extends BorderPane {
    private PlayerProfileManager profileManager;
    private TextField nameField;
    private ListView<String> decksListView;
    private ObservableList<String> decksItems;
    private Label statusLabel;
    private Button setActiveButton;
    private Button removeDeckButton;
    
    public PlayerProfileView() {
        setPadding(new Insets(20));
        
        // Create UI components
        VBox leftPanel = createProfilePanel();
        VBox rightPanel = createDecksPanel();
        
        // Add panels to the layout
        setLeft(leftPanel);
        setRight(rightPanel);
        
        // Initialize with empty profile
        setProfileManager(new PlayerProfileManager());
    }
    
    private VBox createProfilePanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPrefWidth(300);
        
        Label titleLabel = new Label("Perfil del Jugador");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Name field
        Label nameLabel = new Label("Nombre:");
        nameField = new TextField();
        nameField.setPromptText("Ingrese su nombre");
        
        // Register button
        Button registerButton = new Button("Registrar / Actualizar");
        registerButton.setOnAction(e -> registerOrUpdatePlayer());
        
        // Status label
        statusLabel = new Label("Por favor, registre un jugador");
        statusLabel.setTextFill(Color.GRAY);
        
        // Add components to panel
        panel.getChildren().addAll(
            titleLabel,
            new Separator(),
            nameLabel,
            nameField,
            registerButton,
            new Separator(),
            statusLabel
        );
        
        return panel;
    }
    
    private VBox createDecksPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPrefWidth(400);
        
        Label titleLabel = new Label("Mazos");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Decks list
        decksItems = FXCollections.observableArrayList();
        decksListView = new ListView<>(decksItems);
        decksListView.setPrefHeight(300);
        
        // Buttons for deck management
        Button importDeckButton = new Button("Importar Mazo");
        importDeckButton.setOnAction(e -> importDeck());
        
        setActiveButton = new Button("Establecer como Activo");
        setActiveButton.setOnAction(e -> setActiveDeck());
        setActiveButton.setDisable(true);
        
        removeDeckButton = new Button("Eliminar Mazo");
        removeDeckButton.setOnAction(e -> removeDeck());
        removeDeckButton.setDisable(true);
        
        // Enable/disable buttons based on selection
        decksListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean hasSelection = newSelection != null;
            setActiveButton.setDisable(!hasSelection);
            removeDeckButton.setDisable(!hasSelection);
        });
        
        // Button layout
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(importDeckButton, setActiveButton, removeDeckButton);
        
        // Deck details
        Label detailsLabel = new Label("Detalles del Mazo");
        detailsLabel.setStyle("-fx-font-weight: bold;");
        
        TextArea deckDetailsArea = new TextArea();
        deckDetailsArea.setEditable(false);
        deckDetailsArea.setPrefHeight(150);
        
        // Update details when a deck is selected
        decksListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() >= 0 && profileManager != null) {
                Deck selectedDeck = profileManager.getDeck(newVal.intValue());
                if (selectedDeck != null) {
                    StringBuilder details = new StringBuilder();
                    details.append("Unidades: ").append(selectedDeck.getUnitsCount()).append("\n");
                    details.append("Cartas Especiales: ").append(selectedDeck.getSpecialsCount()).append("\n");
                    details.append("Total de Cartas: ").append(selectedDeck.getCardCount());
                    deckDetailsArea.setText(details.toString());
                } else {
                    deckDetailsArea.setText("");
                }
            } else {
                deckDetailsArea.setText("");
            }
        });
        
        // Add components to panel
        panel.getChildren().addAll(
            titleLabel,
            new Separator(),
            decksListView,
            buttonBox,
            detailsLabel,
            deckDetailsArea
        );
        
        return panel;
    }
    
    private void registerOrUpdatePlayer() {
        String name = nameField.getText().trim();
        
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Por favor, ingrese un nombre de jugador.");
            return;
        }
        
        // Update profile manager with new name
        profileManager.setPlayerName(name);
        
        // Update UI
        statusLabel.setText("Jugador registrado: " + name);
        statusLabel.setTextFill(Color.GREEN);
        
        updateDecksList();
    }
    
    private void importDeck() {
        if (profileManager.getPlayerName().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Por favor, registre un jugador primero.");
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar Mazo");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos JSON", "*.json")
        );
        
        File file = fileChooser.showOpenDialog(getScene().getWindow());
        if (file != null) {
            try {
                // Import deck from file
                Deck importedDeck = DeckImporter.importDeckFromJson(file.getAbsolutePath());
                
                // Ask for deck name
                TextInputDialog dialog = new TextInputDialog("Mazo " + (profileManager.getDeckCount() + 1));
                dialog.setTitle("Nombre del Mazo");
                dialog.setHeaderText("Ingrese un nombre para este mazo:");
                dialog.setContentText("Nombre:");
                
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(deckName -> {
                    // Add deck to player profile
                    profileManager.addDeck(importedDeck);
                    
                    // Update UI
                    updateDecksList();
                    
                    // Show success message
                    showAlert(Alert.AlertType.INFORMATION, "Importación Exitosa", 
                        "Mazo importado exitosamente: " + deckName);
                });
                
            } catch (IOException | ParseException e) {
                showAlert(Alert.AlertType.ERROR, "Error de Importación", 
                    "Error al importar el mazo: " + e.getMessage());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", 
                    "Error al procesar el mazo: " + e.getMessage());
            }
        }
    }
    
    private void setActiveDeck() {
        if (profileManager == null) return;
        
        int selectedIndex = decksListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            profileManager.setActiveDeck(selectedIndex);
            updateDecksList();
        }
    }
    
    private void removeDeck() {
        if (profileManager == null) return;
        
        int selectedIndex = decksListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar este mazo?");
            alert.setContentText("Esta acción no se puede deshacer.");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                profileManager.removeDeck(selectedIndex);
                updateDecksList();
            }
        }
    }
    
    private void updateDecksList() {
        decksItems.clear();
        
        if (profileManager != null) {
            int activeDeckIndex = profileManager.getActiveDeckIndex();
            
            for (int i = 0; i < profileManager.getDeckCount(); i++) {
                Deck deck = profileManager.getDeck(i);
                String deckName = "Mazo " + (i + 1);
                
                if (i == activeDeckIndex) {
                    deckName += " (Activo)";
                }
                
                deckName += " - " + deck.getCardCount() + " cartas";
                decksItems.add(deckName);
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
    
    public PlayerProfileManager getProfileManager() {
        return profileManager;
    }
    
    public void setProfileManager(PlayerProfileManager profileManager) {
        this.profileManager = profileManager;
        
        if (profileManager != null && profileManager.hasPlayerProfile()) {
            nameField.setText(profileManager.getPlayerName());
            statusLabel.setText("Jugador: " + profileManager.getPlayerName());
            statusLabel.setTextFill(Color.GREEN);
        } else {
            nameField.clear();
            statusLabel.setText("Por favor, registre un jugador");
            statusLabel.setTextFill(Color.GRAY);
        }
        
        updateDecksList();
    }
} 