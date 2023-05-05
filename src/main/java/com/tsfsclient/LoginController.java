package com.tsfsclient;

import com.google.gson.Gson;
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
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    private final OkHttpClient httpClient;
    private Gson gson;

    public LoginController() {
        errorMessageProperty = new SimpleStringProperty("");
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @FXML public void initialize(){
        loginErrorLabel.textProperty().bind(errorMessageProperty);
        userNameTextField.clear();
        passwordTextField.clear();
    }

    @FXML
    public void loginButtonClicked(ActionEvent event) {
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        if (userName.isEmpty()) {
            errorMessageProperty.set("User name is empty. You can't login with empty user name");
        }
        else {

            if(checkIfValidUser(userName, password)){
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

    private boolean checkIfValidUser(String userName, String password){
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/Access";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder()
                .addQueryParameter("userName", userName)
                        .addQueryParameter("password", password);

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error sending login request", e);
        }
        return true;
    }
}

