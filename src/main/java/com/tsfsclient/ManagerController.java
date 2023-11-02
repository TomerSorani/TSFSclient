package com.tsfsclient;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class ManagerController {
    private final OkHttpClient httpClient;
    private Gson gson;
    private SuperController sController;
    private Stage primaryStage;

    @FXML private Button scanAndUpdateButton;
    @FXML private Button deleteFilesFromDBButton;
    @FXML private Button onButton;
    @FXML private Button offButton;


    public ManagerController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @FXML public void initialize() {}

    public void setWorkerSuperController(SuperController sController, Stage primaryStage){
        this.sController = sController;
        this.primaryStage = primaryStage;
    }

    @FXML public void onActionScanAndUpdateButton(){
        sendRequestToUpdateFiles();
    }

    @FXML private void onActionDeleteFilesFromDBButton(){
        sController.getDashTabContentController().onDeleteAllFilesFromDB();
    }

    @FXML private void onActionOnButton(){
        sendRequestToChangeAvailabilityMode(true);
    }

    @FXML private void onActionOffButton(){
        sendRequestToChangeAvailabilityMode(false);
    }

    private void sendRequestToUpdateFiles(){
        String endPoint = "http://" + sController.IP() + sController.port() + "/TSFS/ScanAndDeleteNonFiles";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .delete()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("error in sendRequestToUpdateFiles: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void sendRequestToChangeAvailabilityMode(boolean mode){
        String endPoint;
        if(mode){
            endPoint = "http://" + sController.IP() + sController.port() + "/TSFS/ActiveAvailability";

        }
        else {
            endPoint = "http://" + sController.IP() + sController.port() + "/TSFS/TurnOffAvailability";

        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            if(mode){
                alert.setContentText("Active");
            }
            else {
                alert.setContentText("Not active");
            }
            alert.showAndWait();
        } catch (IOException e) {
            System.out.println("error in sendRequestToChangeAvailabilityMode: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
