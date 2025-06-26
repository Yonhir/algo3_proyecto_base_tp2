package edu.fiuba.algo3.vistas.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ErrorDialog {
    
    private static boolean isDialogOpen = false;
    
    public static void show(StackPane rootPane, String title, String message) {
        System.out.println("ErrorDialog.show() called");
        
        if (isVisible(rootPane)) {
            System.out.println("Error dialog is already visible on root pane, returning");
            return;
        }
        
        if (isDialogOpen) {
            System.out.println("isDialogOpen flag is true but dialog not visible, resetting flag");
            isDialogOpen = false;
        }
        
        System.out.println("Setting isDialogOpen = true");
        isDialogOpen = true;
        
        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        System.out.println("Window size: " + windowWidth + "x" + windowHeight);
        
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        messageLabel.setWrapText(true);

        Button okButton = new Button("OK");
        okButton.setPrefWidth(100);
        okButton.setPrefHeight(35);
        okButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;");
        okButton.setOnAction(e -> hide(rootPane));
        
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(okButton);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        VBox dialogContent = new VBox(15);
        dialogContent.setAlignment(javafx.geometry.Pos.CENTER);
        dialogContent.getChildren().addAll(titleLabel, messageLabel, buttonBox);
        
        Rectangle dialogBg = new Rectangle(windowWidth * 0.8 , windowHeight * 0.3);
        dialogBg.setFill(Color.rgb(44, 62, 80));
        dialogBg.setArcWidth(10);
        dialogBg.setArcHeight(10);
        
        StackPane dialogPane = new StackPane(dialogBg, dialogContent);
        
        rootPane.getChildren().addAll(overlay, dialogPane);
        
        System.out.println("Error dialog added to root pane, total children: " + rootPane.getChildren().size());
    }
    
    public static void hide(StackPane rootPane) {
        System.out.println("ErrorDialog.hide() called");
        
        if (rootPane.getChildren().size() >= 3) {
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
            System.out.println("Error dialog removed from root pane");
        } else {
            System.out.println("Error dialog not found to hide");
        }
        
        isDialogOpen = false;
        System.out.println("isDialogOpen reset to false");
    }
    
    public static boolean isVisible(StackPane rootPane) {
        int childrenCount = rootPane.getChildren().size();
        return childrenCount >= 3;
    }
} 