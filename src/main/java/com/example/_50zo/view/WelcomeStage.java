package com.example._50zo.view;

import com.example._50zo.controller.FirstStageController;
import com.example._50zo.controller.WelcomeStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
    private WelcomeStageController controller;
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

    public WelcomeStageController getController(){
        return controller;
    }

    /**
     * Internal class that implements the Singleton pattern.
     * Contains a static instance of {@link WelcomeStage}
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
     * Close the welcome window and delete its current instance.
     */
    public static void deleteInstance() {
        if (WelcomeStage.Holder.INSTANCE != null) {
            WelcomeStage.Holder.INSTANCE.close();
            WelcomeStage.Holder.INSTANCE = null;
        }
    }
}

