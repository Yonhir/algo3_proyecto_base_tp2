package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Unit;
import edu.fiuba.algo3.modelo.Modifier;
import edu.fiuba.algo3.modelo.Agil;
import edu.fiuba.algo3.modelo.TightBond;
import edu.fiuba.algo3.modelo.CardJsonConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardCreationView extends VBox {
    private List<Unit> createdCards = new ArrayList<>();
    private ListView<String> cardsList;
    private TextField nameField;
    private TextArea descriptionField;
    private TextField pointsField;
    private CheckBox closeCombatCheck;
    private CheckBox rangedCheck;
    private CheckBox siegeCheck;
    private CheckBox agileCheck;
    private CheckBox tightBondCheck;
    private Button createButton;
    private int editingIndex = -1;

    public CardCreationView() {
        super(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        // Create form fields
        nameField = new TextField();
        nameField.setPromptText("Nombre de la unidad");
        
        descriptionField = new TextArea();
        descriptionField.setPromptText("Descripción de la unidad");
        descriptionField.setPrefRowCount(3);
        
        pointsField = new TextField();
        pointsField.setPromptText("Puntos de la unidad");
        
        // Create checkboxes for unit types
        closeCombatCheck = new CheckBox("Combate Cercano");
        rangedCheck = new CheckBox("A Distancia");
        siegeCheck = new CheckBox("Asedio");
        
        // Create checkbox for agile modifier
        agileCheck = new CheckBox("Ágil");
        
        // Create checkbox for tight bond modifier
        tightBondCheck = new CheckBox("Vínculo");
        
        // Create form layout
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);
        
        form.add(new Label("Nombre:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Descripción:"), 0, 1);
        form.add(descriptionField, 1, 1);
        form.add(new Label("Puntos:"), 0, 2);
        form.add(pointsField, 1, 2);
        form.add(new Label("Tipo:"), 0, 3);
        
        HBox typeBox = new HBox(10);
        typeBox.getChildren().addAll(closeCombatCheck, rangedCheck, siegeCheck);
        form.add(typeBox, 1, 3);
        
        form.add(new Label("Modificador:"), 0, 4);
        HBox modifierBox = new HBox(10);
        modifierBox.getChildren().addAll(agileCheck, tightBondCheck);
        form.add(modifierBox, 1, 4);
        
        // Create cards list view
        cardsList = new ListView<>();
        cardsList.setPrefHeight(200);
        
        // Create buttons for card actions
        createButton = new Button("Crear Carta");
        Button editButton = new Button("Editar Carta");
        Button duplicateButton = new Button("Duplicar Carta");
        
        // Create import/export buttons
        Button exportButton = new Button("Exportar Cartas");
        Button importButton = new Button("Importar Cartas");
        
        // Initially disable buttons that require selection or cards
        editButton.setDisable(true);
        duplicateButton.setDisable(true);
        exportButton.setDisable(true);
        
        // Layout for card action buttons
        HBox cardActionBox = new HBox(10);
        cardActionBox.setAlignment(Pos.CENTER);
        cardActionBox.getChildren().addAll(createButton, editButton, duplicateButton);
        
        // Layout for import/export buttons
        HBox importExportBox = new HBox(10);
        importExportBox.setAlignment(Pos.CENTER);
        importExportBox.getChildren().addAll(importButton, exportButton);
        
        // Enable/disable buttons based on selection
        cardsList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean hasSelection = newSelection != null;
            editButton.setDisable(!hasSelection);
            duplicateButton.setDisable(!hasSelection);
        });
        
        // Enable export button when there are cards
        cardsList.getItems().addListener((javafx.collections.ListChangeListener.Change<? extends String> c) -> {
            exportButton.setDisable(cardsList.getItems().isEmpty());
        });
        
        // Create button action
        createButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String description = descriptionField.getText();
                int points = Integer.parseInt(pointsField.getText());
                boolean isCloseCombat = closeCombatCheck.isSelected();
                boolean isRanged = rangedCheck.isSelected();
                boolean isSiege = siegeCheck.isSelected();
                boolean isAgile = agileCheck.isSelected();
                boolean isTightBond = tightBondCheck.isSelected();
                
                List<Modifier> modifiers = new ArrayList<>();
                if (isAgile) {
                    modifiers.add(new Agil());
                }
                if (isTightBond) {
                    modifiers.add(new TightBond());
                }
                
                if (editingIndex >= 0) {
                    // We're editing an existing card
                    // Create a new card with the updated properties
                    Unit updatedCard = new Unit(name, description, points, isCloseCombat, isRanged, isSiege, modifiers);
                    
                    // Replace the old card with the updated one
                    createdCards.set(editingIndex, updatedCard);
                    
                    // Update the list view
                    cardsList.getItems().set(editingIndex, String.format("%s - %d puntos", name, updatedCard.calculatePoints()));
                    
                    // Reset editing state
                    editingIndex = -1;
                    createButton.setText("Crear Carta");
                    
                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Carta Actualizada");
                    alert.setHeaderText(null);
                    alert.setContentText("La carta ha sido actualizada exitosamente.");
                    alert.showAndWait();
                } else {
                    // We're creating a new card
                    Unit newCard = new Unit(name, description, points, isCloseCombat, isRanged, isSiege, modifiers);
                    createdCards.add(newCard);
                    
                    // Update list view
                    cardsList.getItems().add(String.format("%s - %d puntos", name, newCard.calculatePoints()));
                    
                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Carta Creada");
                    alert.setHeaderText(null);
                    alert.setContentText("La carta ha sido creada exitosamente.");
                    alert.showAndWait();
                }
                
                // Clear form
                nameField.clear();
                descriptionField.clear();
                pointsField.clear();
                closeCombatCheck.setSelected(false);
                rangedCheck.setSelected(false);
                siegeCheck.setSelected(false);
                agileCheck.setSelected(false);
                tightBondCheck.setSelected(false);
                
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor ingrese un número válido para los puntos.");
                alert.showAndWait();
            }
        });
        
        // Edit button action
        editButton.setOnAction(e -> {
            int selectedIndex = cardsList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < createdCards.size()) {
                Unit selectedCard = createdCards.get(selectedIndex);
                
                // Populate form fields with card data
                nameField.setText(selectedCard.getName());
                descriptionField.setText(selectedCard.getDescription());
                pointsField.setText(String.valueOf(selectedCard.getBasePoints()));
                
                // Set unit types
                closeCombatCheck.setSelected(selectedCard.canBeInCloseCombat());
                rangedCheck.setSelected(selectedCard.canBeInRanged());
                siegeCheck.setSelected(selectedCard.canBeInSiege());
                
                // Set modifiers
                agileCheck.setSelected(selectedCard.haveModifier(new Agil()));
                tightBondCheck.setSelected(selectedCard.haveModifier(new TightBond()));
                
                // Set editing state
                editingIndex = selectedIndex;
                createButton.setText("Guardar Cambios");
            }
        });
        
        // Duplicate button action
        duplicateButton.setOnAction(e -> {
            int selectedIndex = cardsList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < createdCards.size()) {
                Unit originalCard = createdCards.get(selectedIndex);
                
                // Create a copy of the card
                List<Modifier> copiedModifiers = new ArrayList<>();
                // Check for modifiers by testing against new instances
                if (originalCard.haveModifier(new Agil())) {
                    copiedModifiers.add(new Agil());
                }
                if (originalCard.haveModifier(new TightBond())) {
                    copiedModifiers.add(new TightBond());
                }
                
                // Get name and add "(copia)" suffix
                String originalName = originalCard.getName();
                String copiedName = originalName + " (copia)";
                
                // Create new unit with copied properties
                Unit duplicatedCard = new Unit(
                    copiedName,
                    originalCard.getDescription(),
                    originalCard.getBasePoints(),
                    originalCard.canBeInCloseCombat(),
                    originalCard.canBeInRanged(),
                    originalCard.canBeInSiege(),
                    copiedModifiers
                );
                
                createdCards.add(duplicatedCard);
                
                // Update list view
                cardsList.getItems().add(String.format("%s - %d puntos", 
                    copiedName, duplicatedCard.calculatePoints()));
                
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Carta Duplicada");
                alert.setHeaderText(null);
                alert.setContentText("La carta ha sido duplicada exitosamente.");
                alert.showAndWait();
            }
        });
        
        // Export button action
        exportButton.setOnAction(e -> {
            if (!createdCards.isEmpty()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Guardar Cartas");
                fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Archivos JSON", "*.json")
                );
                fileChooser.setInitialFileName("cartas.json");
                
                File file = fileChooser.showSaveDialog(getScene().getWindow());
                if (file != null) {
                    try {
                        CardJsonConverter.exportCards(createdCards, file.getAbsolutePath());
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Exportación Exitosa");
                        alert.setHeaderText(null);
                        alert.setContentText("Las cartas han sido exportadas exitosamente a: " + file.getAbsolutePath());
                        alert.showAndWait();
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error de Exportación");
                        alert.setHeaderText(null);
                        alert.setContentText("Error al exportar las cartas: " + ex.getMessage());
                        alert.showAndWait();
                    }
                }
            }
        });
        
        // Import button action
        importButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importar Cartas");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos JSON", "*.json")
            );
            
            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                try {
                    List<Unit> importedCards = CardJsonConverter.importCards(file.getAbsolutePath());
                    
                    if (!importedCards.isEmpty()) {
                        // Confirm import
                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirmar Importación");
                        confirmAlert.setHeaderText("Se encontraron " + importedCards.size() + " cartas para importar");
                        confirmAlert.setContentText("¿Desea añadir estas cartas a su colección actual?");
                        
                        ButtonType addButton = new ButtonType("Añadir a existentes");
                        ButtonType replaceButton = new ButtonType("Reemplazar existentes");
                        ButtonType cancelButton = ButtonType.CANCEL;
                        
                        confirmAlert.getButtonTypes().setAll(addButton, replaceButton, cancelButton);
                        
                        confirmAlert.showAndWait().ifPresent(response -> {
                            if (response == addButton) {
                                // Add imported cards to existing ones
                                for (Unit card : importedCards) {
                                    createdCards.add(card);
                                    cardsList.getItems().add(String.format("%s - %d puntos", 
                                        card.getName(), card.calculatePoints()));
                                }
                                showImportSuccessMessage(importedCards.size(), "añadidas a");
                            } else if (response == replaceButton) {
                                // Replace existing cards with imported ones
                                createdCards.clear();
                                cardsList.getItems().clear();
                                
                                for (Unit card : importedCards) {
                                    createdCards.add(card);
                                    cardsList.getItems().add(String.format("%s - %d puntos", 
                                        card.getName(), card.calculatePoints()));
                                }
                                showImportSuccessMessage(importedCards.size(), "cargadas en");
                            }
                        });
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Importación Vacía");
                        alert.setHeaderText(null);
                        alert.setContentText("El archivo no contiene cartas para importar.");
                        alert.showAndWait();
                    }
                } catch (IOException | ParseException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de Importación");
                    alert.setHeaderText(null);
                    alert.setContentText("Error al importar las cartas: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });
        
        // Add components to main container
        getChildren().addAll(
            new Label("Crear Nueva Carta"),
            form,
            cardActionBox,
            new Separator(),
            importExportBox,
            new Label("Cartas Creadas:"),
            cardsList
        );
    }
    
    private void showImportSuccessMessage(int count, String action) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Importación Exitosa");
        alert.setHeaderText(null);
        alert.setContentText(count + " cartas han sido " + action + " su colección exitosamente.");
        alert.showAndWait();
    }
    
    public List<Unit> getCreatedCards() {
        return createdCards;
    }
}
