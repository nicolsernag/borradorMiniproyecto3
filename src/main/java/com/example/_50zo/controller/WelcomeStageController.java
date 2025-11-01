package com.example._50zo.controller;

import com.example._50zo.view.GameStage2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeStageController {

    @FXML
    void handlePlay(ActionEvent event) throws IOException {
        GameStage2.getInstance().getController().initGame(1);
    }
    @FXML
    void handlePlay2p(ActionEvent event) throws IOException {
        GameStage2.getInstance().getController().initGame(2);
    }

    @FXML
    void handlePlay3p(ActionEvent event) throws IOException {
        GameStage2.getInstance().getController().initGame(3);
    }
}
