package com.tsfsclient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AddFileController {
    private SuperController sController;
    private Stage primaryStage;

    @FXML
    private Button addFileButton;
    @FXML
    private ChoiceBox<City> cityChoiceBox;


    @FXML public void initialize() {
        cityChoiceBox.getItems().addAll(City.values());
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
        try {
            copyFile(absolutePath);
        }
        catch (Exception exception){exception.getMessage();
        }
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


    public void setWorkerSuperController(SuperController sController, Stage primaryStage){
        this.sController = sController;
        this.primaryStage = primaryStage;
    }
}
