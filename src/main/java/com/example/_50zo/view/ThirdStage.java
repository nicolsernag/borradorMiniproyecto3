package com.example._50zo.view;

import com.example._50zo.controller.GameStage2Controller;
import com.example._50zo.controller.ThirdStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the third stage of the 50zo application.
 * <p>
 * This stage corresponds to the victory screen that appears when
 * the player wins the game. It loads its UI from an FXML file,
 * initializes the corresponding controller, and uses a Singleton
 * pattern to ensure that only one instance of the stage is active
 * at any given time.
 */
public class ThirdStage extends Stage {
    /** Controller associated with this stage. */
    private ThirdStageController controller;

    /**
     * Private constructor used to initialize the stage.
     * <p>
     * Loads the FXML layout, retrieves the assigned controller,
     * sets up the stage properties, and displays the window.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    private ThirdStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_50zo/thirdview.fxml"));
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
     * @return a {@link ThirdStageController} instance
     */
    public ThirdStageController getController(){
        return controller;
    }

    /**
     * Internal static holder used to implement a lazy-loaded,
     * thread-safe Singleton.
     */
    private static class Holder{
        private static ThirdStage INSTANCE=null;
    }

    /**
     * Returns the Singleton instance of {@code ThirdStage}.
     * <p>
     * Creates a new instance if it does not already exist.
     *
     * @return the single {@code ThirdStage} instance
     * @throws IOException if initialization fails
     */
    public static ThirdStage getInstance() throws IOException{
        ThirdStage.Holder.INSTANCE = ThirdStage.Holder.INSTANCE != null ?
                ThirdStage.Holder.INSTANCE : new ThirdStage();
        return ThirdStage.Holder.INSTANCE;
    }

    /**
     * Deletes the current instance of this stage.
     * <p>
     * Closes the window and resets the stored instance to {@code null},
     * allowing a new instance to be created later.
     */
    public static void deleteInstance(){
        ThirdStage.Holder.INSTANCE.close();
        ThirdStage.Holder.INSTANCE = null;
    }
}

