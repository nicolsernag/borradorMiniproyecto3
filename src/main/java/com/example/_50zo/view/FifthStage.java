package com.example._50zo.view;

import com.example._50zo.controller.FifthStageController;
import com.example._50zo.controller.FourthStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the fifth stage of the 50zo application.
 * <p>
 * This class follows a Singleton-based stage management approach, ensuring that
 * only one instance of this window exists at a time. It loads its corresponding
 * FXML layout, initializes its controller, and displays the stage when created.
 */
public class FifthStage extends Stage {
    /** Controller associated with the fifth stage UI. */
    private FifthStageController controller;

    /**
     * Private constructor used to initialize the stage.
     * <p>
     * Loads the FXML layout, retrieves the controller, configures
     * window properties, and displays the stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    private FifthStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_50zo/fifthview.fxml"));
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
     * @return the {@link FifthStageController} instance
     */
    public FifthStageController getController(){
        return controller;
    }

    /**
     * Holder class used to implement the Singleton pattern
     * in a thread-safe and lazy-loaded manner.
     */
    private static class Holder{
        private static FifthStage INSTANCE=null;
    }


    /**
     * Returns the current instance of the {@code FifthStage}.
     * <p>
     * If no instance exists, a new stage is created.
     *
     * @return the singleton instance of {@code FifthStage}
     * @throws IOException if the stage fails to initialize
     */
    public static FifthStage getInstance() throws IOException{
        FifthStage.Holder.INSTANCE = FifthStage.Holder.INSTANCE != null ?
                FifthStage.Holder.INSTANCE : new FifthStage();
        return FifthStage.Holder.INSTANCE;
    }

    /**
     * Deletes the current instance of the stage.
     * <p>
     * This method closes the window and resets the stored instance
     * so a new one can be created later.
     */
    public static void deleteInstance(){
        FifthStage.Holder.INSTANCE.close();
        FifthStage.Holder.INSTANCE = null;
    }

}
