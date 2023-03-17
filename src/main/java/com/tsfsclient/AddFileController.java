package com.tsfsclient;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tsfsclient.rappers.FileContainer;
import com.tsfsclient.rappers.LinesContainer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.*;
import com.google.gson.Gson;


public class AddFileController {
    private final int port = 1995;
    private final OkHttpClient httpClient;
    private SuperController sController;
    private Stage primaryStage;
    private Gson gson;
    private List<String> selectedLines;

    @FXML
    private Button addFileButton;
    @FXML
    private ListView<City> cityListView;
    @FXML
    private ListView<City> selectedCityListView;
    @FXML
    private ListView<String> linesListView;
    @FXML
    private ListView<String> selectedLinesListView;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField fileNameTextField;

    private SimpleBooleanProperty selectedDatePickerProperty;

    public AddFileController() {
        httpClient = new OkHttpClient();
        gson = new Gson();
        selectedLines = new LinkedList<>();
        selectedDatePickerProperty = new SimpleBooleanProperty(false);

    }

    @FXML
    public void initialize() {
        cityListView.getItems().addAll(City.sort());
        selectedDatePickerProperty.bind(startDatePicker.valueProperty().isNull().or(endDatePicker.valueProperty().isNull()));

//        addFileButton.disableProperty().bind(selectedDatePickerProperty.and(
//                fileNameTextField.textProperty().isEmpty()));

    }

    @FXML
    private void onMouseClickCityListView() {
        City city = cityListView.getSelectionModel().getSelectedItem();
        if (city != null) {
            selectedCityListView.getItems().add(cityListView.getSelectionModel().getSelectedItem());
            cityListView.getItems().remove(city);
            City.sort(cityListView.getItems());
            City.sort(selectedCityListView.getItems());
            List<String> lineList = sendRequestForLinesInCity(city);
            lineList.forEach(line -> {
                if (!linesListView.getItems().contains(line)) {
                    linesListView.getItems().add(line);
                }
            });
        }
    }

    @FXML
    private void onMouseClickSelectedCityListView() {
        City city = selectedCityListView.getSelectionModel().getSelectedItem();
        if (city != null) {
            cityListView.getItems().add(selectedCityListView.getSelectionModel().getSelectedItem());
            selectedCityListView.getItems().remove(city);
            City.sort(cityListView.getItems());
            City.sort(selectedCityListView.getItems());
        }
    }

    @FXML
    private void onMouseClickSelectedLinesListView() {
        String line = selectedLinesListView.getSelectionModel().getSelectedItem();
        if (line != null) {
            linesListView.getItems().add(selectedLinesListView.getSelectionModel().getSelectedItem());
            selectedLinesListView.getItems().remove(line);
            Collections.sort(linesListView.getItems());
            Collections.sort(selectedLinesListView.getItems());
        }
    }

    @FXML
    private void onMouseClickLinesListView() {
        String line = linesListView.getSelectionModel().getSelectedItem();
        if (line != null) {
            selectedLinesListView.getItems().add(linesListView.getSelectionModel().getSelectedItem());
            linesListView.getItems().remove(line);
            Collections.sort(linesListView.getItems());
            Collections.sort(selectedLinesListView.getItems());
        }
    }

    @FXML
    void AddFileButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("WORD files", "*.docx"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();

        FileContainer fileContainer = createFileContainer(absolutePath);
        //send to server (ok / bad)
        try {
            sendRequestToAddFile(fileContainer);
            System.out.println("file added");
        }catch (IOException exception){
            System.out.println("Error");
        }


        try {
            copyFile(absolutePath);
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    private FileContainer createFileContainer(String absolutePath){
        String fileName = fileNameTextField.getText();

        LocalDate startSelectedDate = startDatePicker.getValue();
        Integer startDay = startSelectedDate.getDayOfMonth();
        Integer startMonth = startSelectedDate.getMonth().getValue();
        Integer startYear = startSelectedDate.getYear();
        LocalDate endSelectedDate = endDatePicker.getValue();
        Integer endDay = endSelectedDate.getDayOfMonth();
        Integer endMonth = endSelectedDate.getMonth().getValue();
        Integer endYear = endSelectedDate.getYear();

        List<String> citiesList = selectedCityListView.getItems().stream().map(Enum::name).collect(Collectors.toList());
        List<String>  linesList = selectedLinesListView.getItems().stream().toList();

        return new FileContainer(absolutePath,fileName,startDay.toString(),startMonth.toString(),startYear.toString(),
                endDay.toString(),endMonth.toString(),endYear.toString(),citiesList,linesList);
    }

    private void copyFile(String absolutePath) throws IOException {
        File original = new File(absolutePath);

        // Create a folder named "copied" in the root directory of the project
        String rootDir = System.getProperty("user.dir");
        File copiedFolder = new File(rootDir, "copied");
        copiedFolder.mkdir();

        // Create a destination file inside the "copied" folder with the same name as the original file
        File dest = new File(copiedFolder, original.getName());

        // Copy the file
        Files.copy(original.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public void setWorkerSuperController(SuperController sController, Stage primaryStage) {
        this.sController = sController;
        this.primaryStage = primaryStage;
    }

    private List<String> sendRequestForLinesInCity(City city) {
        List<String> linesList = null;
        String endPoint = "http://localhost:" + port + "/TSFS/GetLinesAccordingToCity";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();
        urlBuilder.addQueryParameter("city", String.valueOf(city.name()));
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {

            String responseBody = response.body().string();
            linesList = gson.fromJson(responseBody, new TypeToken<List<String>>() {
            }.getType());

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return linesList;
    }

    private void sendRequestToAddFile(FileContainer fileContainer) throws IOException {
        String endPoint = "http://localhost:" + port + "/TSFS/AddFile";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();


        RequestBody requestBody = new FormBody.Builder().add("file", gson.toJson(fileContainer)).build();

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

}
