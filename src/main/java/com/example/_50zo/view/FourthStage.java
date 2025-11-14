package com.example._50zo.view;

import com.example._50zo.controller.FourthStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the fourth stage of the 50zo application.
 * <p>
 * This stage is displayed when the player loses the game. It uses
 * a Singleton approach to ensure only one instance of this stage
 * exists at any given time. Upon construction, it loads its FXML layout,
 * initializes its controller, and displays the window.
 */
public class FourthStage extends Stage {
    /** Controller associated with this stage. */
    private FourthStageController controller;

    /**
     * Private constructor used to initialize the stage.
     * <p>
     * Loads the corresponding FXML file, assigns the controller,
     * configures the window properties, and displays the stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    private FourthStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_50zo/fourthview.fxml"));
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
     * Returns the controller associated with this stage.
     *
     * @return a {@link FourthStageController} instance
     */
    public FourthStageController getController(){
        return controller;
    }

    /**
     * Holder class used to implement
     * a thread-safe lazy-loaded Singleton pattern.
     */
    private static class Holder{
        private static FourthStage INSTANCE=null;
    }

    /**
     * Returns the Singleton instance of {@code FourthStage}.
     * <p>
     * If no instance exists, it creates a new one. Otherwise, it returns
     * the existing instance.
     *
     * @return the unique {@code FourthStage} instance
     * @throws IOException if initialization fails
     */
    public static FourthStage getInstance() throws IOException{
        FourthStage.Holder.INSTANCE = FourthStage.Holder.INSTANCE != null ?
                FourthStage.Holder.INSTANCE : new FourthStage();
        return FourthStage.Holder.INSTANCE;
    }

    /**
     * Deletes the current instance of this stage.
     * <p>
     * Closes the window and sets the stored instance to {@code null}
     * to allow future recreation.
     */
    public static void deleteInstance(){
        FourthStage.Holder.INSTANCE.close();
        FourthStage.Holder.INSTANCE = null;
    }
}


