package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.ThirdStage;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Controller class for the third stage of the application.
 * This controller manages user interactions within the third stage,
 * including navigating back to the first stage.
 */

public class ThirdStageController {


    @FXML
    /**
     * Handles the action when the "VOLVER" button is pressed.
     * This method transitions the application back to the FirstStage.
     * @param event
     * @throws IOException
     */
    private void getBack(ActionEvent event) throws IOException {
        ThirdStage.deleteInstance();
        FirstStage.getInstance();
    }

    }

