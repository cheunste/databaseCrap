package com.company.Controller;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 8/5/2018.
 */
public class DBSelectorController implements Initializable {
    private static final int DOUBLE_CLICK = 2;
    ListView<String> listView;
    ArrayList<String> listofDB;
    @FXML
    JFXListView<String> listPane;

    @FXML
    private AnchorPane dbSelectorPane;

    @FXML
    private void openMenu() {
    }

    @FXML
    private void exportMenu() throws Exception {
        String selectedDB = listPane.getSelectionModel().getSelectedItem();
        //ExportVarexpScene.display(selectedDB);
        if (selectedDB == null) {

        } else {
            ExportSceneController exportScene = new ExportSceneController();
            exportScene.display(selectedDB);
        }
    }

    @FXML
    private void importMenu() throws Exception {
        //ImportVarexpScene.display(listPane.getItems());
        ImportSceneController importScene = new ImportSceneController();
        importScene.display();
    }

    @FXML
    private void settingsMenu() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Add some dummy data. Needs to be an arraylist. In the future, this will be where you call the getDatabase method
        listofDB = new ArrayList<>();
        String[] temp = new String[]{"information_schema", "feedback", "mysql", "performance_schema", "sakila", "sys", "test2", "testdb", "twin_buttes_2", "world"};
        for (String item : temp) {
            listofDB.add(item);
        }
        listPane.getItems().addAll(listofDB);

        listPane.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                listPane.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                String databaseSelected = listPane.getSelectionModel().getSelectedItem();
                //When double clicked, this means you should open the database and allow users to edit the DB. This also means you should close
                //the current scene and open up another scene (Make a method for this)
                //TODO: make a method to open up a new scene given the database name
                if (e.getClickCount() == DOUBLE_CLICK) {
                    System.out.println("Double clicked on: " + databaseSelected);
                    openTagEditScene();
                    //Consume the event just incase weird shit happens
                    e.consume();
                }
            }

        });
    }

    public void openTagEditScene() {

    }
}
