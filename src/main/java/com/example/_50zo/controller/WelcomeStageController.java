package com.example._50zo.controller;

import com.example._50zo.view.GameStage2;
import com.example._50zo.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.example._50zo.view.WelcomeStage;

import java.io.IOException;

//GameStage2.getInstance().getController().initGame(3);

/**
 * Controller class for the welcome stage of the application.
 * This controller manages user interactions within the welcome stage,
 * including selecting the number of machine players.
 */
public class WelcomeStageController {

    @FXML
    /**
     * Handles the action when a player selection button is pressed.
     * This method retrieves the number of machine players selected by the user,
     * initializes the game with that number, and transitions to the game stage.
     * @param event The action event triggered by the button press.
     * @throws IOException If an I/O error occurs during the transition.
     */
    void onSelectPlayers(ActionEvent event) throws IOException {
        Button clickedBtn = (Button) event.getSource();
        int numMachinePlayers = Integer.parseInt(clickedBtn.getText());
        System.out.println("Máquinas seleccionadas: " + numMachinePlayers);

        try{
            GameStage2 game = GameStage2.getInstance();
            WelcomeStage.deleteInstance();
            game.getController().setNumMachinePlayers(numMachinePlayers);
            game.getController().initGame();
            WelcomeStage.deleteInstance();
        } catch (Exception e) {
            System.err.println("Error al iniciar el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
