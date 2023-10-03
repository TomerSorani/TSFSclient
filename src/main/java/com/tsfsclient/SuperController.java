package com.tsfsclient;

import com.google.gson.Gson;
import javafx.beans.property.SimpleBooleanProperty;
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

    @FXML private ScrollPane dashTabContent;
    @FXML private DashController dashTabContentController;
    @FXML private ScrollPane addFileTabContent;
    @FXML private AddFileController addFileTabContentController;
    @FXML private ScrollPane ManagerTabContent;
    @FXML private ManagerController ManagerTabContentController;
    @FXML private TabPane controlTabPane;
    @FXML private Tab ManagerTab;
    @FXML private Tab AddFileTab;

    private String userName;
    private SimpleBooleanProperty managerProperty;
    private SimpleBooleanProperty addDeleteFileAllowProperty;

    public SuperController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
        managerProperty = new SimpleBooleanProperty(true);
        addDeleteFileAllowProperty = new SimpleBooleanProperty(true);
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
        AddFileTab.disableProperty().bind(addDeleteFileAllowProperty.and(managerProperty));
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        initialize();
    }

    public void SetUserName(String userName){
        this.userName = userName;
        configPermissionsAccordingToUser();
        dashTabContentController.refreshButtonClicked();
    }

    public void ChangeColor(){
        dashTabContentController.changeMode();
    }

    private void configPermissionsAccordingToUser(){
        if(userName.equalsIgnoreCase("tomer") ||
                userName.equalsIgnoreCase("one piece")){
            managerProperty.setValue(false);
            dashTabContentController.ChangeManagerProperties(true);
        }
        if(userName.equalsIgnoreCase("tomer") ||
                userName.equalsIgnoreCase("control room")||
                userName.equalsIgnoreCase("bakara")||
                userName.equalsIgnoreCase("tihnun")){
            addDeleteFileAllowProperty.setValue(false);
            dashTabContentController.ChangeAddAndDeleteFileAllowProperties(true);
        }
    }

    public static int port() {
        return port;
    }

    public boolean isManagerProperty() {
        return managerProperty.get();
    }

    public SimpleBooleanProperty managerPropertyProperty() {
        return managerProperty;
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
