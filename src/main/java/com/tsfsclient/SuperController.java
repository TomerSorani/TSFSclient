package com.tsfsclient;

import com.google.gson.Gson;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

    @FXML private ScrollPane dashTabContent;
    @FXML private DashController dashTabContentController;
    @FXML private ScrollPane addFileTabContent;
    @FXML private AddFileController addFileTabContentController;
    @FXML private ScrollPane ManagerTabContent;
    @FXML private ManagerController ManagerTabContentController;
    @FXML private TabPane controlTabPane;
    @FXML private Tab ManagerTab;

    private String userName;
    private SimpleBooleanProperty managerProperty;

    public SuperController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
        managerProperty = new SimpleBooleanProperty(true);
    }

    @FXML public void initialize() {
        if(dashTabContentController!=null){
            dashTabContentController.setWorkerSuperController(this, primaryStage);
        }
        if(addFileTabContentController!=null){
            addFileTabContentController.setWorkerSuperController(this, primaryStage);
        }
        if(ManagerTabContentController!=null){
            ManagerTabContentController.setWorkerSuperController(this, primaryStage);
        }

        ManagerTab.disableProperty().bind(managerProperty);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        initialize();
    }

    public void SetUserName(String userName){
        this.userName = userName;
        configPermissionsAccordingToUser();
    }

    private void configPermissionsAccordingToUser(){
        if(userName.equalsIgnoreCase("tomer")){
            managerProperty.setValue(false);
            dashTabContentController.ChangeProperties(true);
        }
    }

    public static int port() {
        return port;
    }

    public DashController getDashTabContentController() {
        return dashTabContentController;
    }

    public AddFileController getAddFileTabContentController() {
        return addFileTabContentController;
    }

    public ManagerController getManagerTabContentController() {
        return ManagerTabContentController;
    }
}
