package com.example._50zo;

import com.example._50zo.view.FirstStage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException  {
            FirstStage.getInstance();
    }

}

