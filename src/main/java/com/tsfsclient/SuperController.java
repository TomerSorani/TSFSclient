package com.tsfsclient;

import com.google.gson.Gson;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.URL;

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
    @FXML private MannagerController ManagerTabContentController;
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


//        try{
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            URL url = getClass().getResource("dashController.fxml");
//            fxmlLoader.setLocation(url);
//            assert url != null;
//            Parent root = fxmlLoader.load(url.openStream());
//            dashController = fxmlLoader.getController();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

//        try{
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            URL url = getClass().getResource("addFileController.fxml");
//            fxmlLoader.setLocation(url);
//            assert url != null;
//            Parent root = fxmlLoader.load(url.openStream());
//            addFileController = fxmlLoader.getController();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

//        try{
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            URL url = getClass().getResource("managerController.fxml");
//            fxmlLoader.setLocation(url);
//            assert url != null;
//            Parent root = fxmlLoader.load(url.openStream());
//            managerController = fxmlLoader.getController();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }



//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashController.fxml"));
//            StackPane dashPane = (StackPane) loader.load();
//            dashController = loader.getController();
//            dashControllerTabContent.setContent(dashPane);
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("addFileController.fxml"));
//            StackPane dashPane = (StackPane) loader.load();
//            addFileController = loader.getController();
//            addFileControllerTabContent.setContent(dashPane);
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("managerController.fxml"));
//            StackPane dashPane = (StackPane) loader.load();
//            managerController = loader.getController();
//            ManagerControllerTabContent.setContent(dashPane);
//        }catch (IOException e) {
//            e.printStackTrace();
//        }


        if(dashTabContentController!=null){
            dashTabContentController.setWorkerSuperController(this, primaryStage);
        }
        if(ManagerTabContentController!=null){
            ManagerTabContentController.setWorkerSuperController(this, primaryStage);
        }

        ManagerTab.disableProperty().bind(managerProperty);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        initialize(); //?
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



}
