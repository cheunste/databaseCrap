package com.company.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Stephen on 8/13/2018.
 */
public class ImportSceneController {

    private Stage importWindow;
    private String PATH_TO_SCENE = "../View/ImportScene.fxml";
    private String databaseName;
    private boolean databaseNameBoolean;
    private boolean chosenFileBoolean;

    @FXML
    private JFXButton browseButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton importButton;

    @FXML
    private JFXTextField importDatabaseName;

    @FXML
    private Label outputLabel;

    //The following initializes all the buttons to their proper actions or functions
    @FXML
    private void initialize() {

        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

        browseButton.setOnAction(e -> {
            //String importFile = getFilePath();
            getFilePath();
        });

        importButton.disableProperty().bind(
                Bindings.or(importDatabaseName.textProperty().isEmpty(), outputLabel.textProperty().isEmpty())
        );
        importButton.setOnAction(e -> {

        });
    }

    public void display() throws Exception {

        importWindow = new Stage();
        importWindow.initModality(Modality.APPLICATION_MODAL);
        importDatabaseName = new JFXTextField();
        browseButton = new JFXButton();
        importButton = new JFXButton();

        Parent root = FXMLLoader.load(getClass().getResource(PATH_TO_SCENE));
        importWindow.setScene(new Scene(root));
        importWindow.setTitle("Choose Export Directory:");
        importWindow.setTitle("Import Varexp");

        importWindow.showAndWait();

    }

    //Gets a file path from the user
    private void getFilePath() {
        String filePath;
        FileChooser fc = new FileChooser();
        fc.setTitle("Import Varexp file");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Varexp File", "*.txt")
        );
        File selectedFile = fc.showOpenDialog(importWindow);
        if (selectedFile != null) {
            outputLabel.setText(selectedFile.getAbsolutePath());
        }
        //Reaching here doesn't really mean anything useful. It just means the user hit the cancel button for
        //whatever the reason
        else {
            outputLabel.setText("");
        }
    }
}
