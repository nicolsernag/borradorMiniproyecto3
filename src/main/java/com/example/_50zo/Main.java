package com.example._50zo;

import com.example._50zo.view.FirstStage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point of the 50zo application.
 * <p>
 * This class initializes the JavaFX environment and launches
 * the first stage of the graphical interface. The application
 * follows a multi-stage structure, each one managed by its own
 * controller and implemented using the Singleton pattern.
 */
public class Main extends Application {

    /**
     * Standard Java entry point.
     * <p>
     * Delegates control to {@link Application#launch(String...)} to
     * start the JavaFX application lifecycle.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Called automatically by the JavaFX runtime when the application starts.
     * <p>
     * This method opens the first window of the program by calling
     * {@link FirstStage#getInstance()}, which creates or retrieves
     * the main stage of the application.
     *
     * @param primaryStage the default stage provided by JavaFX (not used directly)
     * @throws IOException if the FXML file for the first stage cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws IOException  {
            FirstStage.getInstance();
    }

}

