package com.tsfsclient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private Button LoginButton;
    @FXML
    private Label loginErrorLabel;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField userNameTextField;
    private StringProperty errorMessageProperty;
    private Stage primaryStage;
    private Scene sControllerScene;
    private SuperController sController;

    public LoginController() {
        errorMessageProperty = new SimpleStringProperty("");
    }

    @FXML public void initialize(){
        loginErrorLabel.textProperty().bind(errorMessageProperty);
        userNameTextField.clear();
        passwordTextField.clear();
    }

    @FXML
    public void loginButtonClicked(ActionEvent event) {
        String userName = userNameTextField.getText();
        if (userName.isEmpty()) {
            errorMessageProperty.set("User name is empty. You can't login with empty user name");
        }
        else {
            if(checkIfValidUser()){
                primaryStage.setScene(sControllerScene);
                primaryStage.show();
            }
            else {
                errorMessageProperty.set("Wrong User name or password");
            }

        }
    }

    public void setPrimaryStage(Stage primaryStageIn){
        primaryStage=primaryStageIn;
        loadSuperScreen();
    }

    private void loadSuperScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL superScreenUrl = getClass().getResource("SuperController.fxml");
        fxmlLoader.setLocation(superScreenUrl);
        try {
            Parent root1 = fxmlLoader.load(superScreenUrl.openStream());
            sController=fxmlLoader.getController();
            sController.setPrimaryStage(primaryStage);
            primaryStage.setTitle("T.S.F.S");
            sControllerScene = new Scene(root1);
            primaryStage.setMinHeight(300f);
            primaryStage.setMinWidth(400f);
        }
        catch (IOException exception){exception.printStackTrace();}
    }

    private boolean checkIfValidUser(){
        return true;
    }
}

