package com.tsfsclient;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;

public class SuperController {
    private Stage primaryStage;
    private static int port = 1995;
    private final OkHttpClient httpClient;
    private Gson gson;

    @FXML private ScrollPane dashControllerTabContent;
    @FXML private DashController dashController;
    @FXML private MannagerController managerController;
    @FXML private TabPane controlTabPane;
    @FXML private Tab ManagerTab;

    public SuperController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @FXML public void initialize() {
        if(dashController!=null){
            dashController.setWorkerSuperController(this, primaryStage);
        }
        if(managerController!=null){
            managerController.setWorkerSuperController(this, primaryStage);
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
