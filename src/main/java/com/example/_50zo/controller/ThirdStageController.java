package com.example._50zo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ThirdStageController {
    @FXML
    private Label winnerName;

    public void setWinnerName(String name){
        winnerName.setText("¡Felicidades " + name + "!");
    }

}
