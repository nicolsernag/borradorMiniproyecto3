package com.example._50zo.controller;

import com.example._50zo.model.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.List;
import com.example._50zo.model.CardEnum;

public class GameStage2Controller {
    @FXML
    private GridPane GPMachine1;

    @FXML
    private GridPane GPMachine2;

    @FXML
    private GridPane ǴridPaneHumanPlayer;

    @FXML
    private ImageView Table;

    @FXML
    private ImageView Deck;

    private List<Player> players;
    private Deck deck;

    private Player machinePlayer;

    private Player humanPlayer;

    private Game50 game50;

    private int posInitCardToShow;

    public void initGame(int nMachine) {
        deck = new Deck();
        deck.shuffle();

        players = new ArrayList<>();
        players.add(new Player("Humano"));

        for (int i = 0; i < nMachine; i++)
            players.add(machinePlayer);

        for (int c = 0; c < 5; c++)
            for (Player p : players)
                p.addCard(deck.takeCard());

        printCards(nMachine);
    }


    /**
     * Prints the cards of the players.
     */
    private void printCards(int nMachines){
        this.ǴridPaneHumanPlayer.getChildren().clear();
        this.GPMachine1.getChildren().clear();
        int maxCards = Math.min(this.machinePlayer.getCardsPlayer().size(), 4);

        for(int i=0; i < maxCards; i++) {
            ImageView backCardUno = new ImageView(CardEnum.CARD_FACE_DOWN.getFilePath());
            backCardUno.setY(16);
            backCardUno.setFitHeight(90);
            backCardUno.setFitWidth(60);

            this.gridPaneCardsMachine.add(backCardUno, i, 0);
        }
    }
}
