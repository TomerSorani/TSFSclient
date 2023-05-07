package com.tsfsclient;

import com.google.gson.Gson;
import javafx.fxml.FXML;
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

    public ManagerController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @FXML public void initialize() {}

    public void setWorkerSuperController(SuperController sController, Stage primaryStage){
        this.sController = sController;
        this.primaryStage = primaryStage;
    }

    @FXML private void onActionScanAndUpdateButton(){
        sendRequestToUpdateFiles();
    }

    @FXML private void onActionDeleteFilesFromDBButton(){
        sController.getDashTabContentController().onDeleteAllFilesFromDB();
    }

    private void sendRequestToUpdateFiles(){
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/ScanAndDeleteNonFiles";
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
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
