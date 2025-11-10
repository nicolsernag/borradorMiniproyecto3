package com.example._50zo.controller;

import com.example._50zo.view.FirstStage;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class FirstStageController {

    @FXML
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

