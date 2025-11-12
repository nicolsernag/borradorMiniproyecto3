package com.example._50zo.controller;

import com.example._50zo.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class FifthStageController {

    @FXML
    private void getBackTwo(ActionEvent event) throws IOException {
        try {
            FirstStage firstStage = FirstStage.getInstance();
            firstStage.show();

            FifthStage.deleteInstance();

        } catch (Exception e) {
            System.err.println("Error al abrir la pantalla de bienvenida: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
