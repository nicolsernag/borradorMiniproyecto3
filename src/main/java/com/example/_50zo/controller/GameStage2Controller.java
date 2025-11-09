package com.example._50zo.controller;

import com.example._50zo.model.*;
import com.example._50zo.model.Threads.MachineThread;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import com.example._50zo.model.CardEnum;

public class GameStage2Controller {

    private int numMachinePlayers; // valor que viene del menú

    @FXML private GridPane gridMachine1;
    @FXML private GridPane gridMachine2;
    @FXML private GridPane gridMachine3;

    @FXML
    private GridPane gridPaneHumanPlayer;

    @FXML
    private ImageView Table;

    @FXML
    private Label labelTable;

    @FXML
    private Label msgHumanPlayer;

    @FXML
    private ImageView Deck;

    private Deck deck;
    private Table table;

    private Player humanPlayer;
    private List<Player> machinePlayers = new ArrayList<>();

    private Game50 game;

    private final Image backCardImage = new Image(
            getClass().getResourceAsStream(CardEnum.CARD_FACE_DOWN.getFilePath())
    );

    private List<GridPane> machineGrids = new ArrayList<>();

    public void setNumMachinePlayers(int num) {
        this.numMachinePlayers = num;
    }

    public void initVariables(){
        humanPlayer = new Player();
        deck = new Deck();
        table = new Table();
        this.game = new Game50(humanPlayer, deck, table);
        machineGrids = List.of(gridMachine1, gridMachine2, gridMachine3);
    }

    public void initGame() {
        initVariables();
        createMachinePlayers();
        deck.shuffle();

        dealCard();
        showHumanCards();
        showMachineCards();
        dealCardTable();
    }

    private void createMachinePlayers(){
        for (int i = 0; i < numMachinePlayers; i++) {
            machinePlayers.add(new Player());
        }
    }

    private ImageView createBackCardImageView() {
        ImageView img = new ImageView(backCardImage);
        img.setFitWidth(82);
        img.setFitHeight(128);
        return img;
    }


    private void dealCard(){
        for (int i = 0; i < 4; i++) {
            Card c = deck.takeCard();
            humanPlayer.addCard(c);
            System.out.println("Humano recibe: " + c.getValue());
        }

        for(Player machine : machinePlayers){
            for (int i = 0; i < 4; i++) {
                machine.addCard(deck.takeCard());
            }
        }
    }

    private void dealCardTable(){
        Card card = this.deck.takeCard();
        table.addCardOnTheTable(card);
        Card firstCard = table.getCurrentCardOnTheTable();
        Table.setImage(firstCard.getImage());
        counterTable = firstCard.getNumericValue(counterTable);
        labelTable.setText("Total mesa: " + counterTable);
    }


    private void showMachineCards(){
        for (int i = 0; i < 3; i++) {
            GridPane grid = machineGrids.get(i);

            if(i < machinePlayers.size()){
                grid.setVisible(true);
                grid.getChildren().clear();
                Player cpu = machinePlayers.get(i);
                int col = 0;
                for (Card card : cpu.getCardsPlayer()) {
                    grid.add(createBackCardImageView(), col++, 0);
                }
            } else {
                grid.setVisible(false);
            }
        }
    }

    private void showHumanCards (){
        gridPaneHumanPlayer.getChildren().clear();
        int col = 0;
        for (Card card : humanPlayer.getCardsPlayer()){
            ImageView img = card.getCard();
            setupCardClick(img, card, col);
            gridPaneHumanPlayer.add(img, col++, 0);
        }
    }

    private boolean humanEliminated = false;

    /**
     * Asigna el evento de clic a una carta en la posición dada.
     */
    private void setupCardClick(ImageView img, Card card, int columnIndex) {
        img.setOnMouseClicked(event -> {
            if (humanEliminated) {
                msgHumanPlayer.setText("Has sido eliminado");
                return;
            }

            int cardValue = card.getNumericValue(counterTable);
            int possibleTotal = counterTable + cardValue;

            // Verificar si al jugar la carta se pasaría de 50
            if (possibleTotal > 50) {
                msgHumanPlayer.setText("Esta carta excede el límite de 50.");

                // Comprobar si el jugador tiene alguna carta jugable
                if (!canPlayAnyCard()) {
                    humanEliminated = true;
                    msgHumanPlayer.setText("No puedes jugar ninguna carta. Has sido eliminado del juego.");
                }
                return;
            }

            // Juega la carta normalmente
            game.playCard(humanPlayer, card);
            Table.setImage(card.getImage());
            updateMesaValue(card);

            // Quitar la carta jugada
            humanPlayer.getCardsPlayer().remove(card);

            // Robar una nueva si hay cartas
            if (!game.getDeck().isEmpty()) {
                Card newCard = game.getDeck().takeCard();
                humanPlayer.addCardAt(columnIndex, newCard);
                ImageView newImg = newCard.getCard();
                setupCardClick(newImg, newCard, columnIndex);
                gridPaneHumanPlayer.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == columnIndex);
                gridPaneHumanPlayer.add(newImg, columnIndex, 0);
            } else {
                gridPaneHumanPlayer.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == columnIndex);
            }
        });
    }

    /**
     * Checks whether the human player can play at least one card
     * without exceeding a total of 50 on the table.
     *
     * @return true if there is a playable card, false if not.
     */
    private boolean canPlayAnyCard() {
        for (Card c : humanPlayer.getCardsPlayer()) {
            if (counterTable + c.getNumericValue(counterTable) <= 50) {
                return true; // tiene al menos una carta válida
            }
        }
        return false; // no puede jugar ninguna carta
    }

    private int counterTable = 0;
    /**
     * Updates the total value of all cards currently placed on the table.
     * <p>
     * When a player places a card on the table, this method adds its numeric value
     * to the accumulated total and updates the {@code Label} displaying the current sum.
     * <p>
     * Note: Face cards (J, Q, K) have a negative value (-10) and will decrease the total.
     *
     * @param card the card that was just placed on the table.
     */
    private void updateMesaValue(Card card) {
        int cardValue = card.getNumericValue(counterTable);
        counterTable += cardValue;
        labelTable.setText("Total mesa: " + counterTable);
    }
}
