package edu.fiuba.algo3.vistas.components;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Reusable exit confirmation dialog component
 */
public class ExitConfirmationDialog {
    
    private static boolean isDialogOpen = false;
    
    public static void show(StackPane rootPane) {
        System.out.println("ExitConfirmationDialog.show() called");
        
        // Check if dialog is actually visible on the root pane
        if (isVisible(rootPane)) {
            System.out.println("Exit dialog is already visible on root pane, returning");
            return;
        }
        
        // Prevent multiple dialogs from being created
        if (isDialogOpen) {
            System.out.println("isDialogOpen flag is true but dialog not visible, resetting flag");
            isDialogOpen = false;
        }
        
        System.out.println("Setting isDialogOpen = true");
        isDialogOpen = true;
        
        // Get the current window size dynamically
        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        System.out.println("Window size: " + windowWidth + "x" + windowHeight);
        
        // Create semi-transparent background overlay that covers the entire window
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        
        // Create confirmation dialog content
        Label titleLabel = new Label("Exit Gwent");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label messageLabel = new Label("Are you sure you want to exit?");
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        
        Label warningLabel = new Label("Any unsaved progress will be lost.");
        warningLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff6b6b;");
        
        // Create dialog buttons
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.setPrefHeight(35);
        cancelButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        cancelButton.setOnAction(e -> hide(rootPane));
        
        Button confirmButton = new Button("Exit");
        confirmButton.setPrefWidth(100);
        confirmButton.setPrefHeight(35);
        confirmButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;");
        confirmButton.setOnAction(e -> {
            isDialogOpen = false; // Reset flag before exiting
            Platform.exit();
        });
        
        // Create button container
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(cancelButton, confirmButton);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Create dialog content
        VBox dialogContent = new VBox(15);
        dialogContent.setAlignment(javafx.geometry.Pos.CENTER);
        dialogContent.getChildren().addAll(titleLabel, messageLabel, warningLabel, buttonBox);
        
        // Create dialog background
        Rectangle dialogBg = new Rectangle(400, 200);
        dialogBg.setFill(Color.rgb(44, 62, 80));
        dialogBg.setArcWidth(10);
        dialogBg.setArcHeight(10);
        
        // Create dialog container
        StackPane dialogPane = new StackPane(dialogBg, dialogContent);
        
        // Add overlay and dialog to root pane
        rootPane.getChildren().addAll(overlay, dialogPane);
        
        System.out.println("Exit dialog added to root pane, total children: " + rootPane.getChildren().size());
    }
    
    public static void hide(StackPane rootPane) {
        System.out.println("ExitConfirmationDialog.hide() called");
        
        // Remove the last two children (overlay and dialog)
        if (rootPane.getChildren().size() >= 3) {
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1); // dialog
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1); // overlay
            System.out.println("Exit dialog removed from root pane");
        } else {
            System.out.println("Exit dialog not found to hide");
        }
        
        // Reset the flag when dialog is hidden
        isDialogOpen = false;
        System.out.println("isDialogOpen reset to false");
    }
    
    /**
     * Check if the dialog is currently visible on the root pane
     */
    public static boolean isVisible(StackPane rootPane) {
        // Check if the dialog components are present in the root pane
        int childrenCount = rootPane.getChildren().size();
        return childrenCount >= 3; // Dialog and overlay should be the last 2 children
    }
} 