package com.company.controller;

import com.company.Database.Buffer;
import com.company.Database.Export;
import com.company.Database.dbConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 7/30/2018.
 */

public class MenuSceneController implements Initializable {

    private static final int DOUBLE_CLICK = 2;
    private static final String DEFAULT_EXPORT_DIRECTORY = "C:\\";
    final ObservableList<String> databaseObservableList = FXCollections.observableArrayList();
    private final FileChooser fileDialog = new FileChooser();
    @FXML
    private ListView<String> databaseListView;
    @FXML
    private Button importBtn;
    @FXML
    private Button exportBtn;
    @FXML
    private Button openBtn;
    private String selectedDatabase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //get a dbConnector object and call the showDatbases() method
        dbConnector db = new dbConnector();
        ArrayList<String> listofDatabase = db.showDatabases();
        this.selectedDatabase = null;

        //Store the list of databases to the observableList
        for (String databaseName : listofDatabase) {
            databaseObservableList.add(databaseName);
        }

        //Bind observable list to the databaseListView
        databaseListView.setItems(databaseObservableList);

        //Set button actions
        importBtn.setOnAction(e -> importMenu());
        exportBtn.setOnAction(e -> exportMenu());

        //This setups a click listener for the listView
        databaseListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    selectedDatabase = databaseListView.getSelectionModel().getSelectedItem();
                    //FWHen double click, this means you should open it and allow users to edit the DB
                    if (event.getClickCount() == DOUBLE_CLICK) {
                        System.out.println("Double clicked on: " + selectedDatabase);
                    }
                }
            }
        });
    }

    //Hanldes event for when import button is clicked
    public void importMenu() {
        try {

        } catch (Exception e) {

        }
    }

    //Hanldes event for when exprot button is clicked
    public void exportMenu() {

        try {
            DirectoryChooser dirDialog = new DirectoryChooser();
            //Configure the properties
            dirDialog.setTitle("Select Destination Directory");
            dirDialog.setInitialDirectory(new File(DEFAULT_EXPORT_DIRECTORY));

            //Show the directory dialog
            //File dir = dirDialog.showDialog(null).toString();
            String dir = dirDialog.showDialog(null).toString();
            if (dir != null && selectedDatabase != null) {
                System.out.println("Selected database: " + selectedDatabase);
                System.out.println("Selected directory: " + dir.toString());
                //The actual export code
                Buffer buffer = new Buffer();
                String databaseName = selectedDatabase;
                Export exp = new Export(databaseName, buffer, dir.toString());
                exp.exportDB();
                System.out.println("Done with exporting to " + dir.toString());
            } else {
                System.out.println("No directory was selected.");
            }
        } catch (Exception e) {

            System.out.println("Action Canceled");
        }
    }

}
