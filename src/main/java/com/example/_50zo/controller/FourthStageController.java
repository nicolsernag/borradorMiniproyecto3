package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.FourthStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Controller class for the fourth stage of the 50zo application.
 */

public class FourthStageController {
    @FXML
    /**
     * Handles the action when the "VOLVER" button is pressed.
     * This method transitions the application back to the FirstStage.
     * @param event
     * @throws IOException
     */
    private void getBackAgain(ActionEvent event) throws IOException {
        FourthStage.deleteInstance();
        FirstStage.getInstance();
    }


}
