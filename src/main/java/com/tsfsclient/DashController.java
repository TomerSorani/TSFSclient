package com.tsfsclient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsfsclient.rappers.FileContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashController {
    private final OkHttpClient httpClient;
    private Gson gson;
    private SuperController sController;
    private Stage primaryStage;
    private List<FileContainer> fileList;

    @FXML private TableView<FileTableViewRow> FileTableView;
    @FXML private TableColumn<FileTableViewRow, String> fileNameCol;
    @FXML private TableColumn<FileTableViewRow, String> linesCol;
    @FXML private TableColumn<FileTableViewRow, String> citiesCol;
    @FXML private Button refreshButton;

    public DashController() {
        fileList = new ArrayList<>();
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @FXML public void initialize() {

    }

    @FXML
    public void refreshButtonClicked(ActionEvent event){
        sendRefreshRequest();
    }

    public void setWorkerSuperController(SuperController sController, Stage primaryStage){
        this.sController = sController;
        this.primaryStage = primaryStage;
    }

    private void sendRefreshRequest(){
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/GetFiles";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {

            String responseBody = response.body().string();
            FileContainer[] fileContainers = gson.fromJson(responseBody, FileContainer[].class);

            Arrays.stream(fileContainers)
                    .filter(file -> !fileList.contains(file))
                    .filter(file -> fileList.stream().noneMatch(existingFile -> existingFile.getFileName().equals(file.getFileName())))
                    .forEach(fileList::add);


            updateTableView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void updateTableView() {
        FileTableView.getItems().removeAll(FileTableView.getItems());
        for (FileContainer file : fileList) {
            // Get the values to add to the table
            String fileName = file.getFileName();
            String citiesArray = Arrays.toString(file.getCitiesArray());
            String linesArray = Arrays.toString(file.getLinesArray());

            // Create a new row in the table
            TableView.TableViewSelectionModel selectionModel = FileTableView.getSelectionModel();
            selectionModel.clearSelection();
            ObservableList<FileTableViewRow> data = FileTableView.getItems();
            FileTableViewRow fileTableViewRow = new FileTableViewRow(file.getFileName(), file.getCitiesArray(), file.getLinesArray());
            data.add(fileTableViewRow);
            int row = data.indexOf(fileTableViewRow);
            selectionModel.select(row);

            // Set the cell values for the new row
            FileTableView.getSelectionModel().selectLast();
            FileTableView.scrollTo(FileTableView.getSelectionModel().getSelectedItem());
            FileTableView.getFocusModel().focus(row);
            fileNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
                    return new SimpleStringProperty(param.getValue().getFileName());
                }
            });
            linesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
                    return new SimpleStringProperty(citiesArray);
                }
            });
            citiesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
                    return new SimpleStringProperty(linesArray);
                }
            });
            FileTableView.edit(row, fileNameCol);
            FileTableView.edit(row, linesCol);
            FileTableView.edit(row, citiesCol);
        }
    }

}
