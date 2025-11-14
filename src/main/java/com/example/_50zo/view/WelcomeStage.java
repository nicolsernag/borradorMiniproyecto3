package com.example._50zo.view;

import com.example._50zo.controller.FirstStageController;
import com.example._50zo.controller.WelcomeStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the Welcome Stage of the 50zo application.
 * <p>
 * This stage is displayed after the initial start screen and allows
 * the user to choose the number of players or proceed to the next step
 * in the game setup. It loads its layout through an FXML file and
 * uses a Singleton pattern to ensure that only one instance is ever created.
 */
public class WelcomeStage extends Stage {
    private WelcomeStageController controller;

    /**
     * Private constructor that initializes and displays the WelcomeStage.
     * <p>
     * This method loads the FXML file, configures the scene,
     * sets window properties such as title and icon, and displays the stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    private WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_50zo/welcomeStage.fxml"));
        Parent root= loader.load();
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
     * Returns the controller associated with this stage.
     *
     * @return a {@link WelcomeStageController} instance
     */
    public WelcomeStageController getController(){
        return controller;
    }

    /**
     * Internal helper class used to implement a lazy-loaded Singleton instance.
     */
    private static class Holder{
        private static WelcomeStage INSTANCE=null;
    }

    /**
     * Returns the unique instance of {@link WelcomeStage}.
     * If no previous instance exists, a new one is created; otherwise,
     * the existing one is returned. This method ensures that only one welcome window is active at a time.
     * @return The unique instance of {@link WelcomeStage}.
     * @throws IOException if an error occurs while creating a new instance.
     */
    public static WelcomeStage getInstance() throws IOException{
        Holder.INSTANCE = Holder.INSTANCE != null ?
                Holder.INSTANCE : new WelcomeStage();
        return Holder.INSTANCE;
    }

    /**
     * Closes the welcome stage and resets the stored Singleton instance.
     * <p>
     * After calling this method, a new instance may be created when
     * {@link #getInstance()} is called again.
     */
    public static void deleteInstance() {
        if (WelcomeStage.Holder.INSTANCE != null) {
            WelcomeStage.Holder.INSTANCE.close();
            WelcomeStage.Holder.INSTANCE = null;
        }
    }
}

