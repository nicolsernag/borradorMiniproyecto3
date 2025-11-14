package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller class for the first stage of the application.
 * This controller manages user interactions within the first stage,
 * including transitioning to the welcome stage.
 */
public class FirstStageController {

    /**
     * Handles the action triggered when the "START" button is pressed.
     * <p>
     * This method opens the {@link WelcomeStage}, which allows the user
     * to select the number of machine players, and then closes the current
     * {@link FirstStage}.
     *
     * @param event the action event produced by the user interaction
     */
    @FXML
    private void handlePlay(ActionEvent event) {
        try {
            // Open the player selection screen
            WelcomeStage.getInstance().getController();

            // Close the current screen
            FirstStage.deleteInstance();

        } catch (Exception e) {
            System.err.println("Error al abrir la pantalla de bienvenida: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

