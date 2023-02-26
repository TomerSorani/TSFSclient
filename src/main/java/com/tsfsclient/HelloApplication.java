package com.tsfsclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //primaryStage.setTitle("Hello!");
        //primaryStage.setScene(scene);
        //primaryStage.show();


        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("Login.fxml");
        fxmlLoader.setLocation(url);
        assert url != null;
        Parent root = fxmlLoader.load(url.openStream());

        LoginController loginController = fxmlLoader.getController();
        loginController.setPrimaryStage(primaryStage);
        primaryStage.setTitle("T.S.F.S");
        Scene scene = new Scene(root);
        primaryStage.setMinHeight(300f);
        primaryStage.setMinWidth(400f);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}