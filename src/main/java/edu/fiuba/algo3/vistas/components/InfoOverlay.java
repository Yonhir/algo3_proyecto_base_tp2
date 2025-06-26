package edu.fiuba.algo3.vistas.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InfoOverlay {
    
    private static boolean isDialogOpen = false;
    
    public static void show(StackPane rootPane, String title, String message) {
        
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
        
        // Create info dialog content
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        messageLabel.setWrapText(true);
        
        // Create dialog button
        Button okButton = new Button("OK");
        okButton.setPrefWidth(100);
        okButton.setPrefHeight(35);
        okButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #3498DB; -fx-text-fill: white;");
        okButton.setOnAction(e -> hide(rootPane));
        
        // Create dialog content
        VBox dialogContent = new VBox(15);
        dialogContent.setAlignment(javafx.geometry.Pos.CENTER);
        dialogContent.getChildren().addAll(titleLabel, messageLabel, okButton);
        
        // Create the dialog background
        Rectangle dialogBg = new Rectangle(400, 180);
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