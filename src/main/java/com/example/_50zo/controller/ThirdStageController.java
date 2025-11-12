package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.ThirdStage;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ThirdStageController {


    @FXML
    private void getBack(ActionEvent event) throws IOException {
        ThirdStage.deleteInstance();
        FirstStage.getInstance();
    }

    }

