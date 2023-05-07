package com.tsfsclient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsfsclient.rappers.FileContainer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Desktop;

public class DashController {
    private final OkHttpClient httpClient;
    private Gson gson;
    private SuperController sController;
    private Stage primaryStage;
    private List<FileContainer> fileList;
    private SimpleBooleanProperty managerProperty;
    private SimpleBooleanProperty deleteFileAllowProperty;

    @FXML private TableView<FileTableViewRow> FileTableView;
    @FXML private TableColumn<FileTableViewRow, String> fileNameCol;
    @FXML private TableColumn<FileTableViewRow, String> linesCol;
    @FXML private TableColumn<FileTableViewRow, String> citiesCol;
    @FXML private Button refreshButton;
    @FXML private Button sortByDateButton;
    @FXML private Button deleteFilesFromDBButton;
    @FXML private Button deleteFileButton;
    @FXML private TextField deleteFileTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ChoiceBox<City> cityChoiceBox;
    @FXML private ChoiceBox<String> lineChoiceBox;
    @FXML private CheckBox darkModeCheckBox;
    @FXML private VBox deleteFilesVbox;

    public DashController() {
        fileList = new ArrayList<>();
        httpClient = new OkHttpClient();
        gson = new Gson();
        managerProperty = new SimpleBooleanProperty(false);
        deleteFileAllowProperty = new SimpleBooleanProperty(false);
    }

    @FXML public void initialize() {
        addCitiesToChoiceBox();
        lineChoiceBox.disableProperty().setValue(false);
        deleteFilesFromDBButton.visibleProperty().bind(managerProperty);
        deleteFilesVbox.visibleProperty().bind(deleteFileAllowProperty);
        //deleteFilesFromDBButton.setVisible(false);
    }

    public void ChangeManagerProperties(boolean managerPropertyValue){
        managerProperty.setValue(managerPropertyValue);
        //deleteFilesFromDBButton.setVisible(!deleteFilesFromDBButton.visibleProperty().getValue());
    }

    public void ChangeDeleteFileAllowProperties(boolean DeleteFileAllowPropertyValue){
        deleteFileAllowProperty.setValue(DeleteFileAllowPropertyValue);
    }

    @FXML
    public void refreshButtonClicked(ActionEvent event){
        sendRefreshRequest();
    }

    @FXML
    public void onRowClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            FileTableViewRow fileTableViewRow = FileTableView.getSelectionModel().getSelectedItem();
            if (fileTableViewRow != null) {
                System.out.println("Selected file: " + fileTableViewRow.getFileName());
                tryOpenMessagesDirectoryAndDeleteContent();
                String fileName = fileTableViewRow.getFileName();
                String fileLocation = sendRequestToGetFileLocation(fileName);
                try{
                    moveFileToMessagesDirectory(fileLocation, fileName);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void onSortByDateButton(){
        LocalDate startSelectedDate = startDatePicker.getValue();
        LocalDate endSelectedDate = endDatePicker.getValue();
        sortTableViewByDate(startSelectedDate, endSelectedDate);
    }

    @FXML
    public void onDeleteAllFilesFromDB(){
        sendRequestToDeleteAllFilesFromDB();

        fileList.removeAll(fileList);
        sendRefreshRequest();
    }

    @FXML
    public void onDeleteAllFile(){
        deleteFile();
    }

    @FXML
    private void onSelectedCity(){
        City selectedCity = cityChoiceBox.getSelectionModel().getSelectedItem();
        List<String> linesList = sendRequestForLinesInCity(selectedCity);
        lineChoiceBox.disableProperty().setValue(false);
        onClearLineButton();
        lineChoiceBox.getItems().removeAll(lineChoiceBox.getItems());
        lineChoiceBox.getItems().addAll(linesList);
    }

    @FXML
    private void onSortByCityLineButton(){
        if(lineChoiceBox.getSelectionModel().isEmpty()){
            sortTableViewByCity(cityChoiceBox.getSelectionModel().getSelectedItem());
        }
        else {
            sortTableViewByline(lineChoiceBox.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void onClearLineButton(){
        lineChoiceBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void onActionDarkMode(){
        changeMode();
    }

    private void tryOpenMessagesDirectoryAndDeleteContent(){
        try {
            openMessagesDirectoryAndDeleteContent();
        }catch (Exception e){e.printStackTrace();}
    }

    private void openMessagesDirectoryAndDeleteContent() throws IOException {
        String directoryPath = "C:/messages";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory");
            }
        }

        if (!Desktop.isDesktopSupported()) {
            throw new IOException("Desktop is not supported");
        }

        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.OPEN)) {
            throw new IOException("OPEN action is not supported");
        }

        tryDeleteContentFromDir(directory);
        desktop.open(directory);
    }

    private void tryDeleteContentFromDir(File directory){
        try{
            deleteContentFromDir(directory);
        }catch (Exception e){e.printStackTrace();}
    }

    private void deleteContentFromDir(File directory) throws IOException {
        if (!directory.exists()) {
            throw new IOException("Directory does not exist: " + directory.getAbsolutePath());
        }
        if (!directory.isDirectory()) {
            throw new IOException(directory.getAbsolutePath() + " is not a directory");
        }

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                deleteContentFromDir(file);
            }
            file.delete();
        }
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


            updateTableView(fileList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void sortTableViewByDate(LocalDate startSelectedDate, LocalDate endSelectedDate){
        List<FileContainer> filesOnDate = new ArrayList<>();
        for(FileContainer fileContainer : fileList){
            LocalDate startTime = LocalDate.of(Integer.parseInt(fileContainer.getStartYear()), Integer.parseInt(fileContainer.getStartMonth()), Integer.parseInt(fileContainer.getStartDay()));
            LocalDate endTime = LocalDate.of(Integer.parseInt(fileContainer.getEndYear()), Integer.parseInt(fileContainer.getEndMonth()), Integer.parseInt(fileContainer.getEndDay()));
            if ((startTime.isBefore(endSelectedDate) || startTime.equals(endSelectedDate))
                    && (endTime.isAfter(startSelectedDate) || endTime.equals(startSelectedDate))) {
                filesOnDate.add(fileContainer);
            }
        }
        updateTableView(filesOnDate);
    }

    private void sortTableViewByline(String line){
        List<FileContainer> filesAboutLine = new ArrayList<>();
        for(FileContainer fileContainer : fileList){
            if (Arrays.stream(fileContainer.getLinesArray()).toList().contains(line)) {
                filesAboutLine.add(fileContainer);
            }
        }
        updateTableView(filesAboutLine);
    }

    private void sortTableViewByCity(City city){
        List<FileContainer> filesAboutLine = new ArrayList<>();
        for(FileContainer fileContainer : fileList){
            if (Arrays.stream(fileContainer.getCitiesArray()).toList().contains(city.name())) {
                filesAboutLine.add(fileContainer);
            }
        }
        updateTableView(filesAboutLine);
    }

    private void updateTableView(List<FileContainer> fileContainerList) {
        FileTableView.getItems().removeAll(FileTableView.getItems());
        for (FileContainer file : fileContainerList) {
//            // Get the values to add to the table
//            String fileName = file.getFileName();
//            String citiesArray = Arrays.toString(file.getCitiesArray());
//            String linesArray = Arrays.toString(file.getLinesArray());

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
            linesCol.setCellValueFactory(new PropertyValueFactory<>("lines"));
            citiesCol.setCellValueFactory(new PropertyValueFactory<>("cities"));

//            linesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
//                @Override
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
//                    return new SimpleStringProperty(citiesArray);
//                }
//            });

//            linesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
//                @Override
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
//                    String[] linesArray = param.getValue().getLinesArray();
//                    String linesString = Arrays.toString(linesArray);
//                    return new SimpleStringProperty(linesString.substring(1, linesString.length() - 1));
//                }
//            });

//            citiesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
//                @Override
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
//                    return new SimpleStringProperty(linesArray);
//                }
//            });

//            citiesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FileTableViewRow, String>, ObservableValue<String>>() {
//                @Override
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<FileTableViewRow, String> param) {
//                    String[] citiesArray = param.getValue().getCitiesArray();
//                    String citiesString = Arrays.toString(citiesArray);
//                    return new SimpleStringProperty(citiesString.substring(1, citiesString.length() - 1));
//                }
//            });
            FileTableView.edit(row, fileNameCol);
            FileTableView.edit(row, linesCol);
            FileTableView.edit(row, citiesCol);
        }
    }

    private String sendRequestToGetFileLocation(String fileName) {
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/GetFileLocationAccordingToFileName";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();
        urlBuilder.addQueryParameter("fileName", fileName); // Add query parameter for fileName
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();
        String fileLocation = null;
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            fileLocation = response.body().string();
            fileLocation = fileLocation.replaceAll("\"", ""); // remove the quotation marks

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return fileLocation;
    }

    private void moveFileToMessagesDirectory(String fileLocation, String fileName) throws IOException {
        String directoryPath = "C:/messages";

        // Create the destination directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a destination file inside the "messages" directory with the same name as the original file
        File dest = new File(directoryPath, fileName+".docx");

        // Copy the file
        Files.copy(new File(fileLocation).toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private void addCitiesToChoiceBox(){
        cityChoiceBox.getItems().addAll(City.sort());
    }

    private List<String> sendRequestForLinesInCity(City city) {
        List<String> linesList = null;
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/GetLinesAccordingToCity";
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

    private void sendRequestToDeleteAllFilesFromDB() {
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/DeleteFilesFromDB";
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

    private void deleteFile(){
        if(!deleteFileTextField.getText().isEmpty())
        {
            String fileToDeleteName = deleteFileTextField.getText();
            if (fileList.stream().anyMatch(fileContainer -> fileContainer.getFileName().equals(fileToDeleteName))){
                sendRequestToDeleteFile(fileToDeleteName);
                fileList.remove(GetFileContainerByFileName(fileToDeleteName));
                sendRefreshRequest();
            }

            deleteFileTextField.clear();
        }
    }

    private void sendRequestToDeleteFile(String fileToDeleteName) {
        String endPoint = "http://localhost:" + sController.port() + "/TSFS/DeleteFile";
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder();
//        JsonObject json = new JsonObject();
//        json.addProperty("fileName", fileToDeleteName);
//        String jsonStr = gson.toJson(json);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
//        Request request = new Request.Builder()
//                .url(urlBuilder.build().toString())
//                .delete(requestBody)
//                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endPoint).newBuilder()
                .addQueryParameter("fileName", fileToDeleteName);
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
            throw new RuntimeException("Error sending DELETE request", e);
        }
    }

    public FileContainer GetFileContainerByFileName(String fileName){
        FileContainer res = null;
        for(FileContainer fileContainer:fileList){
            if(fileContainer.getFileName().equals(fileName)){
                res = fileContainer;
                break;
            }
        }

        return res;
    }

    private void changeMode(){
        Scene scene=darkModeCheckBox.getScene();
        scene.getStylesheets().clear();
        if(darkModeCheckBox.isSelected()){
            scene.getStylesheets().add(getClass().getResource("darkMode.css").toExternalForm());
        }
    }
}
