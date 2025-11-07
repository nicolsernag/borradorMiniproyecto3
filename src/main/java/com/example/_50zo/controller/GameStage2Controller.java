package com.example._50zo.controller;

import com.example._50zo.model.*;
import javafx.fxml.FXML;
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
        humanPlayer = new Player();
        createMachinePlayers();
        deck = new Deck();
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

    /**
     * Asigna el evento de clic a una carta en la posición dada.
     */
    private void setupCardClick(ImageView img, Card card, int columnIndex) {
        img.setOnMouseClicked(event -> {
            // El jugador pone la carta sobre la mesa
            game.playCard(humanPlayer, card);
            Table.setImage(card.getImage());

            // Quitar la carta jugada de la mano
            humanPlayer.getCardsPlayer().remove(card);

            // Si el mazo tiene cartas, tomar una nueva
            if (!game.getDeck().isEmpty()) {
                Card newCard = game.getDeck().takeCard();
                humanPlayer.addCardAt(columnIndex, newCard);

                ImageView newImg = newCard.getCard();
                setupCardClick(newImg, newCard, columnIndex);//evento recursivo para la nueva carta

                // Reemplazar visualmente solo esa posición
                gridPaneHumanPlayer.getChildren().removeIf(
                        node -> GridPane.getColumnIndex(node) == columnIndex
                );
                gridPaneHumanPlayer.add(newImg, columnIndex, 0);

            } else {
                // Si no hay cartas, quitar visualmente la carta jugada
                gridPaneHumanPlayer.getChildren().removeIf(
                        node -> GridPane.getColumnIndex(node) == columnIndex
                );
            }
        });
    }
}
