package com.tsfsclient;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class SuperController {
    private Stage primaryStage;

    @FXML private ScrollPane dashControllerTabContent;
    @FXML private DashController dashController;


    @FXML public void initialize() {
        if(dashController!=null){
            dashController.setWorkerSuperController(this, primaryStage);
        }

    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }


}
