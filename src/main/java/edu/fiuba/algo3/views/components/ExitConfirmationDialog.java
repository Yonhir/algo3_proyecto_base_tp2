package edu.fiuba.algo3.views.components;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ExitConfirmationDialog {
    // Dialog constants
    private static final double OVERLAY_OPACITY = 0.7;
    private static final double DIALOG_WIDTH = 400;
    private static final double DIALOG_HEIGHT = 200;
    private static final double DIALOG_CORNER_RADIUS = 10;
    private static final double BUTTON_SPACING = 20;
    private static final double CONTENT_SPACING = 15;
    private static final int MIN_CHILDREN_COUNT = 3;
    
    // Button constants
    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 35;
    
    private static boolean isDialogOpen = false;
    private static StackPane currentRootPane = null;
    
    public static void show(StackPane rootPane) {
        
        if (isDialogOpen && currentRootPane == rootPane) {
            return;
        }
        
        // Close any existing dialog
        if (isDialogOpen) {
            hideInternal();
        }
        
        isDialogOpen = true;
        currentRootPane = rootPane;
        
        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(OVERLAY_OPACITY);
        
        Label titleLabel = new Label("Exit Gwent");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label messageLabel = new Label("Are you sure you want to exit?");
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        
        Label warningLabel = new Label("Any unsaved progress will be lost.");
        warningLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff6b6b;");
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(BUTTON_WIDTH);
        cancelButton.setPrefHeight(BUTTON_HEIGHT);
        cancelButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        cancelButton.setOnAction(e -> hide(rootPane));
        
        Button confirmButton = new Button("Exit");
        confirmButton.setPrefWidth(BUTTON_WIDTH);
        confirmButton.setPrefHeight(BUTTON_HEIGHT);
        confirmButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;");
        confirmButton.setOnAction(e -> {
            isDialogOpen = false;
            Platform.exit();
        });
        
        HBox buttonBox = new HBox(BUTTON_SPACING);
        buttonBox.getChildren().addAll(cancelButton, confirmButton);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        VBox dialogContent = new VBox(CONTENT_SPACING);
        dialogContent.setAlignment(javafx.geometry.Pos.CENTER);
        dialogContent.getChildren().addAll(titleLabel, messageLabel, warningLabel, buttonBox);
        
        Rectangle dialogBg = new Rectangle(DIALOG_WIDTH, DIALOG_HEIGHT);
        dialogBg.setFill(Color.rgb(44, 62, 80));
        dialogBg.setArcWidth(DIALOG_CORNER_RADIUS);
        dialogBg.setArcHeight(DIALOG_CORNER_RADIUS);
        
        StackPane dialogPane = new StackPane(dialogBg, dialogContent);
        
        rootPane.getChildren().addAll(overlay, dialogPane);
    }
    
    public static void hide(StackPane rootPane) {
        if (isDialogOpen && currentRootPane == rootPane) {
            hideInternal();
        }
    }
    
    private static void hideInternal() {
        if (currentRootPane != null && currentRootPane.getChildren().size() >= MIN_CHILDREN_COUNT) {
            currentRootPane.getChildren().remove(currentRootPane.getChildren().size() - 1);
            currentRootPane.getChildren().remove(currentRootPane.getChildren().size() - 1);
        }
        isDialogOpen = false;
        currentRootPane = null;
    }
}
