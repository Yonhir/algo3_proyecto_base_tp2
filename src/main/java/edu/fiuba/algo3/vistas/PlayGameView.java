package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.List;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Player;
import edu.fiuba.algo3.modelo.Card;
import edu.fiuba.algo3.modelo.Deck;
import edu.fiuba.algo3.modelo.Row;
import edu.fiuba.algo3.modelo.CloseCombat;
import edu.fiuba.algo3.modelo.Ranged;
import edu.fiuba.algo3.modelo.Siege;
import edu.fiuba.algo3.modelo.SpecialZone;

public class PlayGameView extends BorderPane {
    private final PlayersManager playersManager;
    private ComboBox<String> player1Combo;
    private ComboBox<String> player2Combo;
    private Button startGameButton;
    private VBox selectionBox;
    private Label messageLabel;
    private Player player1;
    private Player player2;
    private int discardsLeftP1 = 2;
    private int discardsLeftP2 = 2;
    private List<Card> discardedP1 = new ArrayList<>();
    private List<Card> discardedP2 = new ArrayList<>();
    private VBox prepBox;
    private int prepPhase = 0; // 0: P1 discards, 1: P2 discards, 2: done
    private Deck deck1;
    private Deck deck2;

    public PlayGameView(PlayersManager playersManager) {
        this.playersManager = playersManager;
        setupPlayerSelection();
    }

    private void setupPlayerSelection() {
        List<PlayerProfileManager> players = playersManager.getPlayers();
        selectionBox = new VBox(15);
        selectionBox.setAlignment(Pos.CENTER);
        selectionBox.setPadding(new Insets(40));

        Label selectLabel = new Label("Selecciona dos jugadores para jugar:");
        player1Combo = new ComboBox<>();
        player2Combo = new ComboBox<>();
        player1Combo.setPromptText("Jugador 1");
        player2Combo.setPromptText("Jugador 2");

        for (PlayerProfileManager ppm : players) {
            player1Combo.getItems().add(ppm.getPlayerName());
            player2Combo.getItems().add(ppm.getPlayerName());
        }

        startGameButton = new Button("Comenzar Partida");
        startGameButton.setDisable(true);
        messageLabel = new Label("");

        if (players.size() < 2) {
            messageLabel.setText("Se requieren al menos dos jugadores para jugar.");
            startGameButton.setDisable(true);
        }

        player1Combo.setOnAction(e -> validateSelection());
        player2Combo.setOnAction(e -> validateSelection());

        startGameButton.setOnAction(e -> showGameArea());

        selectionBox.getChildren().addAll(selectLabel, player1Combo, player2Combo, startGameButton, messageLabel);
        setCenter(selectionBox);
    }

    private void validateSelection() {
        String p1 = player1Combo.getValue();
        String p2 = player2Combo.getValue();
        boolean valid = p1 != null && p2 != null && !p1.equals(p2);
        startGameButton.setDisable(!valid);
        if (p1 != null && p2 != null && p1.equals(p2)) {
            messageLabel.setText("Los jugadores deben ser distintos.");
        } else {
            messageLabel.setText("");
        }
    }

    private void showGameArea() {
        // Initialize model.Player objects
        PlayerProfileManager ppm1 = playersManager.getPlayers().stream().filter(ppm -> ppm.getPlayerName().equals(player1Combo.getValue())).findFirst().orElse(null);
        PlayerProfileManager ppm2 = playersManager.getPlayers().stream().filter(ppm -> ppm.getPlayerName().equals(player2Combo.getValue())).findFirst().orElse(null);
        if (ppm1 == null || ppm2 == null) return;
        deck1 = ppm1.getActiveDeck();
        deck2 = ppm2.getActiveDeck();
        // Create board rows for each player
        List<Row> p1Rows = List.of(new CloseCombat(), new Ranged(), new Siege());
        List<Row> p2Rows = List.of(new CloseCombat(), new Ranged(), new Siege());
        SpecialZone p1Special = new SpecialZone(p1Rows, p1Rows, p1Rows);
        SpecialZone p2Special = new SpecialZone(p2Rows, p2Rows, p2Rows);
        player1 = new Player(ppm1.getPlayerName(), 2, deck1, p1Special, (CloseCombat)p1Rows.get(0), (Ranged)p1Rows.get(1), (Siege)p1Rows.get(2));
        player2 = new Player(ppm2.getPlayerName(), 2, deck2, p2Special, (CloseCombat)p2Rows.get(0), (Ranged)p2Rows.get(1), (Siege)p2Rows.get(2));
        // Deal 10 cards to each hand
        player1.getHand().getNCardsFromDeck(deck1, 10);
        player2.getHand().getNCardsFromDeck(deck2, 10);
        // Start preparation phase
        prepPhase = 0;
        discardsLeftP1 = 2;
        discardsLeftP2 = 2;
        discardedP1.clear();
        discardedP2.clear();
        showDiscardPhase();
    }

    private void showDiscardPhase() {
        prepBox = new VBox(20);
        prepBox.setAlignment(Pos.CENTER);
        prepBox.setPadding(new Insets(40));
        if (prepPhase == 0) {
            prepBox.getChildren().add(new Label("Fase de preparación: " + player1Combo.getValue() + " descarta hasta 2 cartas"));
            ListView<String> handList = new ListView<>();
            for (Card c : player1.getHand().getCards()) handList.getItems().add(c.getName());
            Button discardBtn = new Button("Descartar seleccionada");
            Button doneBtn = new Button("Terminar");
            Label discardsLabel = new Label("Descartes restantes: " + discardsLeftP1);
            discardBtn.setOnAction(e -> {
                int idx = handList.getSelectionModel().getSelectedIndex();
                if (idx >= 0 && discardsLeftP1 > 0) {
                    Card card = player1.getHand().getCards().get(idx);
                    player1.getHand().getCards().remove(idx);
                    discardedP1.add(card);
                    handList.getItems().remove(idx);
                    discardsLeftP1--;
                    // Draw a new card immediately
                    try {
                        List<Card> drawn = deck1.retrieveNRandomCards(1);
                        if (!drawn.isEmpty()) {
                            player1.getHand().getCards().addAll(drawn);
                            handList.getItems().add(drawn.get(0).getName());
                        }
                    } catch (Exception ex) { /* deck empty */ }
                    discardsLabel.setText("Descartes restantes: " + discardsLeftP1);
                }
            });
            doneBtn.setOnAction(e -> {
                // Draw replacements
                for (int i = 0; i < discardedP1.size(); i++) {
                    if (player1.getHand().getCards().size() < 10) {
                        try {
                            List<Card> drawn = deck1.retrieveNRandomCards(1);
                            player1.getHand().getCards().addAll(drawn);
                        } catch (Exception ex) { break; }
                    }
                }
                prepPhase = 1;
                showDiscardPhase();
            });
            prepBox.getChildren().addAll(handList, discardBtn, doneBtn, discardsLabel);
        } else if (prepPhase == 1) {
            prepBox.getChildren().add(new Label("Fase de preparación: " + player2Combo.getValue() + " descarta hasta 2 cartas"));
            ListView<String> handList = new ListView<>();
            for (Card c : player2.getHand().getCards()) handList.getItems().add(c.getName());
            Button discardBtn = new Button("Descartar seleccionada");
            Button doneBtn = new Button("Terminar");
            Label discardsLabel = new Label("Descartes restantes: " + discardsLeftP2);
            discardBtn.setOnAction(e -> {
                int idx = handList.getSelectionModel().getSelectedIndex();
                if (idx >= 0 && discardsLeftP2 > 0) {
                    Card card = player2.getHand().getCards().get(idx);
                    player2.getHand().getCards().remove(idx);
                    discardedP2.add(card);
                    handList.getItems().remove(idx);
                    discardsLeftP2--;
                    // Draw a new card immediately
                    try {
                        List<Card> drawn = deck2.retrieveNRandomCards(1);
                        if (!drawn.isEmpty()) {
                            player2.getHand().getCards().addAll(drawn);
                            handList.getItems().add(drawn.get(0).getName());
                        }
                    } catch (Exception ex) { /* deck empty */ }
                    discardsLabel.setText("Descartes restantes: " + discardsLeftP2);
                }
            });
            doneBtn.setOnAction(e -> {
                // Draw replacements
                for (int i = 0; i < discardedP2.size(); i++) {
                    if (player2.getHand().getCards().size() < 10) {
                        try {
                            List<Card> drawn = deck2.retrieveNRandomCards(1);
                            player2.getHand().getCards().addAll(drawn);
                        } catch (Exception ex) { break; }
                    }
                }
                prepPhase = 2;
                showMainBoard();
            });
            prepBox.getChildren().addAll(handList, discardBtn, doneBtn, discardsLabel);
        }
        setCenter(prepBox);
    }

    private void showMainBoard() {
        VBox board = new VBox(20);
        board.setAlignment(Pos.CENTER);
        board.setPadding(new Insets(40));
        board.getChildren().add(new Label("Tablero y lógica de rondas/turnos en construcción"));
        setCenter(board);
    }
} 