package com.example._50zo.controller;

import com.example._50zo.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller for the fifth stage of the application.
 * <p>
 * This stage typically represents an ending screen or a return menu.
 * The controller manages the navigation back to the initial welcome screen.
 */
public class FifthStageController {

    /**
     * Handles the action of returning to the welcome screen (FirstStage).
     * <p>
     * When the user triggers this event (e.g., by pressing a button),
     * the method loads the first stage and closes the current one.
     *
     * @param event the action event triggered by the user interaction
     * @throws IOException if there is an error while loading the FXML view
     */
    @FXML
    private void getBackTwo(ActionEvent event) throws IOException {
        try {
            // Open the welcome screen
            FirstStage firstStage = FirstStage.getInstance();
            firstStage.show();
            // Close the current stage
            FifthStage.deleteInstance();

        } catch (Exception e) {
            System.err.println("Error al abrir la pantalla de bienvenida: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
