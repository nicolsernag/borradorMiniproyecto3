package com.example._50zo.controller;

import com.example._50zo.model.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private Player humanPlayer;
    private List<Player> machinePlayers = new ArrayList<>();


    private List<GridPane> machineGrids = new ArrayList<>();

    public void setNumMachinePlayers(int num) {
        this.numMachinePlayers = num;
    }

    public void initVariables(){
        machineGrids = List.of(gridMachine1, gridMachine2, gridMachine3);
    }

    public void initGame() {
        initVariables();
        humanPlayer = new Player();
        createMachinePlayers();
        deck = new Deck();
        deck.shuffle();

        dealCard();
        //dealCardMachine();
        showHumanCards();
        showMachineCards();
    }

    private void createMachinePlayers(){
        for (int i = 0; i < numMachinePlayers; i++) {
            machinePlayers.add(new Player());
        }
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

    private void dealCardMachine(){
        for (int i = 0; i < numMachinePlayers; i++) {
            for (Player cpu : machinePlayers) {
                Card cpuCard = deck.takeCard();
                cpu.addCard(cpuCard);
                System.out.println("CPU recibe: " + cpuCard.getValue());
            }
        }
    }

    private void showMachineCards(){
        for (int i = 0; i < 3; i++) {
            GridPane grid = machineGrids.get(i);

            if(i < machinePlayers.size()){
                grid.setVisible(true);
                grid.getChildren().clear();
                Player cpu = machinePlayers.get(i);
                int col = 0;
                for (Card card : cpu.getCardsPlayer()) {  // bocarriba como pediste
                    grid.add(card.getCard(), col++, 0);
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
            gridPaneHumanPlayer.add(card.getCard(), col++, 0);
        }
    }
}
