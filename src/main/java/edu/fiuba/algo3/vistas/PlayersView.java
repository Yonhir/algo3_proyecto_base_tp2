package edu.fiuba.algo3.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * View for managing multiple player profiles.
 */
public class PlayersView extends BorderPane {
    private PlayersManager playersManager;
    private ListView<String> playersListView;
    private ObservableList<String> playersItems;
    private Button editButton;
    private Button removeButton;
    private Button setActiveButton;
    
    public PlayersView(PlayersManager playersManager) {
        this.playersManager = playersManager;
        
        setPadding(new Insets(20));
        
        // Create UI components
        VBox leftPanel = createPlayersListPanel();
        VBox rightPanel = createPlayerDetailsPanel();
        
        // Add panels to the layout
        setLeft(leftPanel);
        setRight(rightPanel);
        
        // Update the players list
        updatePlayersList();
    }
    
    private VBox createPlayersListPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPrefWidth(300);
        
        Label titleLabel = new Label("Jugadores");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Players list
        playersItems = FXCollections.observableArrayList();
        playersListView = new ListView<>(playersItems);
        playersListView.setPrefHeight(400);
        
        // Create new player button
        Button createPlayerButton = new Button("Crear Nuevo Jugador");
        createPlayerButton.setOnAction(e -> createNewPlayer());
        
        // Edit player button
        editButton = new Button("Editar Jugador");
        editButton.setOnAction(e -> editSelectedPlayer());
        editButton.setDisable(true);
        
        // Remove player button
        removeButton = new Button("Eliminar Jugador");
        removeButton.setOnAction(e -> removeSelectedPlayer());
        removeButton.setDisable(true);
        
        // Set active player button
        setActiveButton = new Button("Establecer como Activo");
        setActiveButton.setOnAction(e -> setSelectedPlayerAsActive());
        setActiveButton.setDisable(true);
        
        // Enable/disable buttons based on selection
        playersListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean hasSelection = newSelection != null;
            editButton.setDisable(!hasSelection);
            removeButton.setDisable(!hasSelection);
            setActiveButton.setDisable(!hasSelection);
        });
        
        // Button layout
        HBox buttonBox1 = new HBox(10);
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox1.getChildren().addAll(createPlayerButton, editButton);
        
        HBox buttonBox2 = new HBox(10);
        buttonBox2.setAlignment(Pos.CENTER);
        buttonBox2.getChildren().addAll(removeButton, setActiveButton);
        
        // Add components to panel
        panel.getChildren().addAll(
            titleLabel,
            new Separator(),
            playersListView,
            buttonBox1,
            buttonBox2
        );
        
        return panel;
    }
    
    private VBox createPlayerDetailsPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPrefWidth(400);
        
        Label titleLabel = new Label("Detalles del Jugador");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Player details
        TextArea playerDetailsArea = new TextArea();
        playerDetailsArea.setEditable(false);
        playerDetailsArea.setPrefHeight(300);
        
        // Update details when a player is selected
        playersListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() >= 0) {
                PlayerProfileManager selectedPlayer = playersManager.getPlayer(newVal.intValue());
                if (selectedPlayer != null) {
                    StringBuilder details = new StringBuilder();
                    details.append("Nombre: ").append(selectedPlayer.getPlayerName()).append("\n\n");
                    details.append("Mazos: ").append(selectedPlayer.getDeckCount()).append("\n\n");
                    
                    if (selectedPlayer.getDeckCount() > 0) {
                        details.append("Mazos disponibles:\n");
                        for (int i = 0; i < selectedPlayer.getDeckCount(); i++) {
                            details.append("- Mazo ").append(i + 1);
                            if (i == selectedPlayer.getActiveDeckIndex()) {
                                details.append(" (Activo)");
                            }
                            details.append(": ").append(selectedPlayer.getDeck(i).getCardCount()).append(" cartas\n");
                        }
                    } else {
                        details.append("No tiene mazos creados.");
                    }
                    
                    playerDetailsArea.setText(details.toString());
                } else {
                    playerDetailsArea.setText("");
                }
            } else {
                playerDetailsArea.setText("");
            }
        });
        
        // Add components to panel
        panel.getChildren().addAll(
            titleLabel,
            new Separator(),
            playerDetailsArea
        );
        
        return panel;
    }
    
    private void createNewPlayer() {
        // Show dialog to enter player name
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Crear Nuevo Jugador");
        dialog.setHeaderText("Ingrese el nombre del nuevo jugador:");
        dialog.setContentText("Nombre:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (name.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "El nombre del jugador no puede estar vacío.");
                return;
            }
            
            if (playersManager.playerExists(name)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Ya existe un jugador con ese nombre.");
                return;
            }
            
            // Create new player profile
            PlayerProfileManager newPlayer = new PlayerProfileManager(name);
            playersManager.addPlayer(newPlayer);
            
            // Update UI
            updatePlayersList();
            
            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Jugador Creado", 
                "El jugador ha sido creado exitosamente.");
        });
    }
    
    private void editSelectedPlayer() {
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            PlayerProfileManager selectedPlayer = playersManager.getPlayer(selectedIndex);
            
            // Open player profile view for editing
            PlayerProfileView profileView = new PlayerProfileView();
            profileView.setProfileManager(selectedPlayer);
            
            Scene profileScene = new Scene(profileView, 750, 600);
            
            Stage profileStage = new Stage();
            profileStage.setTitle("Editar Perfil de Jugador");
            profileStage.setScene(profileScene);
            profileStage.show();
            
            // Update UI when profile view is closed
            profileStage.setOnHiding(event -> {
                updatePlayersList();
            });
        }
    }
    
    private void removeSelectedPlayer() {
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            PlayerProfileManager selectedPlayer = playersManager.getPlayer(selectedIndex);
            
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar al jugador " + selectedPlayer.getPlayerName() + "?");
            alert.setContentText("Esta acción no se puede deshacer.");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                playersManager.removePlayer(selectedIndex);
                updatePlayersList();
            }
        }
    }
    
    private void setSelectedPlayerAsActive() {
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            playersManager.setActivePlayer(selectedIndex);
            updatePlayersList();
        }
    }
    
    private void updatePlayersList() {
        playersItems.clear();
        
        int activePlayerIndex = playersManager.getActivePlayerIndex();
        
        for (int i = 0; i < playersManager.getPlayerCount(); i++) {
            PlayerProfileManager player = playersManager.getPlayer(i);
            String playerName = player.getPlayerName();
            
            if (i == activePlayerIndex) {
                playerName += " (Activo)";
            }
            
            playerName += " - " + player.getDeckCount() + " mazos";
            playersItems.add(playerName);
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