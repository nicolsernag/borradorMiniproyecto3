package com.example._50zo.view;

import com.example._50zo.controller.FifthStageController;
import com.example._50zo.controller.FourthStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FifthStage extends Stage {
    private FifthStageController controller;

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


    public FifthStageController getController(){
        return controller;
    }


    private static class Holder{
        private static FifthStage INSTANCE=null;
    }



    public static FifthStage getInstance() throws IOException{
        FifthStage.Holder.INSTANCE = FifthStage.Holder.INSTANCE != null ?
                FifthStage.Holder.INSTANCE : new FifthStage();
        return FifthStage.Holder.INSTANCE;
    }


    public static void deleteInstance(){
        FifthStage.Holder.INSTANCE.close();
        FifthStage.Holder.INSTANCE = null;
    }

}
