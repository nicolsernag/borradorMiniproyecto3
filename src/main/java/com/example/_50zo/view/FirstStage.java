package com.example._50zo.view;

import com.example._50zo.controller.FirstStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstStage extends Stage {
    private FirstStageController controller;
    /**
     * Creates and initializes the main window of the Sudoku game.
     * Associates its controller, configures the scene, the title, and the icon of the window, and finally displays it on the screen.
     * @throws IOException if an error occurs when loading the FXML file.
     */
    private FirstStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_50zo/firstview.fxml"));
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
    public FirstStageController getController(){
        return controller;
    }

    /**
     * Static internal class used to maintain a single instance according to the Singleton pattern.
     */
    private static class Holder{
        private static FirstStage INSTANCE=null;
    }

    public static FirstStage getInstance() throws IOException{
        FirstStage.Holder.INSTANCE = FirstStage.Holder.INSTANCE != null ?
                FirstStage.Holder.INSTANCE : new FirstStage();
        return FirstStage.Holder.INSTANCE;
    }

    /**
     * Closes the current instance of the game window and removes it from the Singleton.
     * This method is used when the player finishes a game or
     * when the game interface needs to be restarted.
     */
    public static void deleteInstance(){
        FirstStage.Holder.INSTANCE.close();
        FirstStage.Holder.INSTANCE = null;
    }
}


