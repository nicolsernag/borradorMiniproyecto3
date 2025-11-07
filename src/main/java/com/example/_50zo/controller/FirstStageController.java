package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class FirstStageController {
    @FXML
    private void handlePlay(ActionEvent event) throws IOException {
        WelcomeStage.getInstance().getController();
        FirstStage.deleteInstance();
    }
}

