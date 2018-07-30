package com.company.controller;

import com.company.Database.dbConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 7/30/2018.
 */

public class MenuSceneController implements Initializable {

    final ObservableList<String> databaseObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> databaseListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //get a dbConnector object and call the showDatbases() method
        dbConnector db = new dbConnector();
        ArrayList<String> listofDatabase = db.showDatabases();

        //Store the list of databases to the observableList
        for (String databaseName : listofDatabase) {
            databaseObservableList.add(databaseName);
        }

        //Bind observable list to the databaseListView
        databaseListView.setItems(databaseObservableList);
    }
}
