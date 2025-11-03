package com.example._50zo.controller;

import com.example._50zo.model.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import com.example._50zo.model.CardEnum;

public class GameStage2Controller {

    private int numMachinePlayers; // valor que viene del menú

    private List<Player> machinePlayers = new ArrayList<>();

    @FXML private GridPane gridMachine1;
    @FXML private GridPane gridMachine2;
    @FXML private GridPane gridMachine3;

    @FXML
    private GridPane gridPaneHumanPlayer;

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

    private List<GridPane> machineGrids = new ArrayList<>();

    public void setNumMachinePlayers(int num) {
        this.numMachinePlayers = num;
    }

    public void initGame() {
        deck = new Deck();
        deck.shuffle();


        humanPlayer = new Player("Human");

        machinePlayers.clear();
        for (int i = 1; i <= numMachinePlayers; i++) {
            machinePlayers.add(new Player("CPU" + i));
        }

        dealCard();

    }

    private void dealCard(){
        for (int i = 0; i < 10; i++) {
            humanPlayer.addCard(deck.takeCard());

            for (Player cpu : machinePlayers) {
                cpu.addCard(deck.takeCard());
            }
        }
    }

}
