package edu.fiuba.algo3.vistas.components;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ExitConfirmationDialog {
    
    private static boolean isDialogOpen = false;
    
    public static void show(StackPane rootPane) {
        
        if (isVisible(rootPane)) {
            return;
        }
        
        if (isDialogOpen) {
            isDialogOpen = false;
        }
        
        isDialogOpen = true;
        
        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        
        Label titleLabel = new Label("Exit Gwent");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label messageLabel = new Label("Are you sure you want to exit?");
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        
        Label warningLabel = new Label("Any unsaved progress will be lost.");
        warningLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff6b6b;");
        
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
            isDialogOpen = false;
            Platform.exit();
        });
        
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(cancelButton, confirmButton);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        VBox dialogContent = new VBox(15);
        dialogContent.setAlignment(javafx.geometry.Pos.CENTER);
        dialogContent.getChildren().addAll(titleLabel, messageLabel, warningLabel, buttonBox);
        
        Rectangle dialogBg = new Rectangle(400, 200);
        dialogBg.setFill(Color.rgb(44, 62, 80));
        dialogBg.setArcWidth(10);
        dialogBg.setArcHeight(10);
        
        StackPane dialogPane = new StackPane(dialogBg, dialogContent);
        
        rootPane.getChildren().addAll(overlay, dialogPane);
    }
    
    public static void hide(StackPane rootPane) {
        if (rootPane.getChildren().size() >= 3) {
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
        }
        isDialogOpen = false;
    }
    
    public static boolean isVisible(StackPane rootPane) {
        int childrenCount = rootPane.getChildren().size();
        return childrenCount >= 3;
    }
}
