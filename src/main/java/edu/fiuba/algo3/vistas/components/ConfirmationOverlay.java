package edu.fiuba.algo3.vistas.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class ConfirmationOverlay {
    
    private static boolean isDialogOpen = false;
    
    public static void show(StackPane rootPane, String title, String message, 
                          Consumer<Void> onAccept, Consumer<Void> onCancel) {
        
        if (isDialogOpen) {
            return;
        }
        
        isDialogOpen = true;
        
        // Get the current window size dynamically
        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        // Create semi-transparent background overlay
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        
        // Create confirmation dialog content
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        messageLabel.setWrapText(true);
        
        // Create dialog buttons
        Button acceptButton = new Button("Accept");
        acceptButton.setPrefWidth(100);
        acceptButton.setPrefHeight(35);
        acceptButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #27AE60; -fx-text-fill: white;");
        acceptButton.setOnAction(e -> {
            hide(rootPane);
            onAccept.accept(null);
        });
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.setPrefHeight(35);
        cancelButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #E74C3C; -fx-text-fill: white;");
        cancelButton.setOnAction(e -> {
            hide(rootPane);
            onCancel.accept(null);
        });
        
        // Create button container
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(acceptButton, cancelButton);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Create dialog content
        VBox dialogContent = new VBox(15);
        dialogContent.setAlignment(javafx.geometry.Pos.CENTER);
        dialogContent.getChildren().addAll(titleLabel, messageLabel, buttonBox);
        
        // Create the dialog background
        Rectangle dialogBg = new Rectangle(450, 200);
        dialogBg.setFill(Color.rgb(44, 62, 80));
        dialogBg.setArcWidth(10);
        dialogBg.setArcHeight(10);
        
        // Create dialog container
        StackPane dialogPane = new StackPane(dialogBg, dialogContent);
        
        // Add overlay and dialog to root pane
        rootPane.getChildren().addAll(overlay, dialogPane);
    }
    
    public static void hide(StackPane rootPane) {
        // Remove the last two children (overlay and dialog)
        if (rootPane.getChildren().size() >= 3) {
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1); // dialog
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1); // overlay
        }
        
        isDialogOpen = false;
    }
} 