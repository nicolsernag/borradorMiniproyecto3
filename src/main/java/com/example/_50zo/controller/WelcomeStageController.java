package com.example._50zo.controller;

import com.example._50zo.view.GameStage2;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

//GameStage2.getInstance().getController().initGame(3);

public class WelcomeStageController {

    @FXML
    void onSelectPlayers(ActionEvent event) throws IOException {
        Button clickedBtn = (Button) event.getSource();
        int numMachinePlayers = Integer.parseInt(clickedBtn.getText());
        System.out.println("Máquinas seleccionadas: " + numMachinePlayers);

        try{
            GameStage2 game = GameStage2.getInstance();
            WelcomeStage.deleteInstance();
            game.getController().setNumMachinePlayers(numMachinePlayers);
            game.getController().initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
