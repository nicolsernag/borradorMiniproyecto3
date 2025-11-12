package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.FourthStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FourthStageController {
    @FXML
    private void getBackAgain(ActionEvent event) throws IOException {
        FourthStage.deleteInstance();
        FirstStage.getInstance();
    }


}
