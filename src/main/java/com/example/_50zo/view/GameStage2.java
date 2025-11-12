package com.example._50zo.view;

import com.example._50zo.controller.GameStage2Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage2 extends Stage {
    private GameStage2Controller controller;
    /**
     * Creates and initializes the main window of the Sudoku game.
     * Associates its controller, configures the scene, the title, and the icon of the window, and finally displays it on the screen.
     * @throws IOException if an error occurs when loading the FXML file.
     */
    private GameStage2() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_50zo/gameStage2p.fxml"));
        Parent root= loader.load();
        controller= loader.getController();
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("50zo");
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource("/com/example/_50zo/mano.png")))
        );
        show();
    }

    /**
     * Get the controller associated with the game view.
     * @return The controller that manages the game logic.
     */
    public GameStage2Controller getController(){
        return controller;
    }

    /**
     * Static internal class used to maintain a single instance according to the Singleton pattern.
     */
    private static class Holder{
        private static GameStage2 INSTANCE=null;
    }

    /**
     * Get the unique instance of SudokuGameStage.
     * If the instance does not exist, a new one is created; otherwise,
     * the existing one is returned. This method ensures that there is onlyone main game window running.
     * @return the single instance of SudokuGameStage.
     * @throws IOException if an error occurs while creating a new instance.
     */
    public static GameStage2 getInstance() throws IOException{
        GameStage2.Holder.INSTANCE = GameStage2.Holder.INSTANCE != null ?
                GameStage2.Holder.INSTANCE : new GameStage2();
        return GameStage2.Holder.INSTANCE;
    }

    /**
     * Closes the current instance of the game window and removes it from the Singleton.
     * This method is used when the player finishes a game or
     * when the game interface needs to be restarted.
     */
    public static void deleteInstance(){
        GameStage2.Holder.INSTANCE.close();
        GameStage2.Holder.INSTANCE = null;
    }
}
