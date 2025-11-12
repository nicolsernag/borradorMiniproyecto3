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

    @FXML
    /**
     * Handles the action when the "INICIAR" button is pressed.
     * This method transitions the application to the WelcomeStage.
     * @param event
     */
    private void handlePlay(ActionEvent event) {
        try {
            // Pasa a la pantalla de selección de jugadores
            WelcomeStage.getInstance().getController();

            // Cierra la pantalla actual
            FirstStage.deleteInstance();

        } catch (Exception e) {
            System.err.println("Error al abrir la pantalla de bienvenida: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

