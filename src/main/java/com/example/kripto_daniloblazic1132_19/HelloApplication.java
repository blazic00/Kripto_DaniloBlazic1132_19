package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import crypto.Korisnik;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();

        Korisnik.setCurrentUser("daco00");
       MyStage.createStage("upload_download-view.fxml");


    }

    public static void main(String[] args) {
        launch();
    }

    public static void dump(String string){
        System.out.println(string);
    }
    public static void dump(int string){
        System.out.println(string);
    }

}