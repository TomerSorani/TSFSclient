package com.tsfsclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class SuperController {
    private Stage primaryStage;
    private static int port = 1995;
    private final OkHttpClient httpClient;
    private Gson gson;

    @FXML private ScrollPane dashControllerTabContent;
    @FXML private DashController dashController;

    public SuperController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @FXML public void initialize() {
        if(dashController!=null){
            dashController.setWorkerSuperController(this, primaryStage);
        }

    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        initialize();
    }

    public static int port() {
        return port;
    }



}
