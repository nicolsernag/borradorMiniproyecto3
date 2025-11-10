package com.example._50zo.view;

import com.example._50zo.controller.ThirdStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FourthStage extends Stage {
    private ThirdStageController controller;

    private FourthStage() throws IOException {
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


    public ThirdStageController getController(){
        return controller;
    }


    private static class Holder{
        private static FourthStage INSTANCE=null;
    }



    public static FourthStage getInstance() throws IOException{
        Holder.INSTANCE = Holder.INSTANCE != null ? Holder.INSTANCE : new FourthStage();
        return Holder.INSTANCE;
    }


    public static void deleteInstance(){
        if (Holder.INSTANCE != null) {
            Holder.INSTANCE.close();
            Holder.INSTANCE = null;
        }
    }
}


